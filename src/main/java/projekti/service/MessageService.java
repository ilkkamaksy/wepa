package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import projekti.model.Message;
import projekti.repository.MessageRepository;
import projekti.model.Account;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private AccountService accountService;
    
    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }
    
    public Page<Message> getAllMessagesPaginated(Pageable pageable) {
        return this.messageRepository.findAll(pageable);
    }
    
    public Page<Message> getMessagesByUserPaginated(Account user, Pageable pageable) {
        return this.messageRepository.findByAccountId(user.getId(), pageable);
    }
    
    public Message getMessageById(Long id) {
        return this.messageRepository.getOne(id);
    }
    
    public Message saveNewMessage(Message message) {
        return this.messageRepository.save(message);
    }
    
    public ModelAndView getMessageFeed(Model model, Account currentUser, Integer page, String slug) {

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("paged", page);
   
        if (!slug.isEmpty()) {
            Account user = this.accountService.getAccountBySlug(slug);
            Pageable pageable = PageRequest.of(page, 25, Sort.by("pub_date_time").descending());
            model.addAttribute("messages", this.getMessagesByUserPaginated(user, pageable));
            model.addAttribute("user", user);
        } else {
            Pageable pageable = PageRequest.of(page, 25, Sort.by("pubDateTime").descending());
            model.addAttribute("messages", this.getAllMessagesPaginated(pageable));
        }

        return new ModelAndView("fragments/feedPartials :: messageFeed");
    }
}
