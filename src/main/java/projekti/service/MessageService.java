package projekti.service;

import java.util.List;
import org.hibernate.engine.spi.EntityEntry;
import org.springframework.transaction.annotation.Transactional;
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
    private CommentService commentService;
    
    @Autowired
    private AccountService accountService;
    
    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }
    
    public Page<Message> getMessagesBatch(Integer page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("pubDateTime").descending());
        Page<Message> messages = this.messageRepository.findAll(pageable);

        this.setupCommentsToMessages(messages);
        
        return messages;
    }
   
    public ModelAndView getMessagesBatch(Model model, Account currentUser, Integer page, String slug) {

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("paged", page);

        if (!slug.isEmpty()) {
            Account user = this.accountService.getAccountBySlug(slug);
            model.addAttribute("messages", this.getMessagesBatchByUser(user, page));
            model.addAttribute("user", user);
        } else {
             model.addAttribute("messages", this.getMessagesBatch(page));
        }

        return new ModelAndView("fragments/feedPartials :: messageFeed");
    }
    
    public Page<Message> getMessagesBatchByUser(Account user, Integer page) {
        Pageable pageable = PageRequest.of(page, 25, Sort.by("pubDateTime").descending());
        Page<Message> messages = this.messageRepository.findByUserId(user.getId(), pageable);
        this.setupCommentsToMessages(messages);
        return messages;
    }
    
    private void setupCommentsToMessages(Page<Message> messages) {
        for(Message message : messages) {
            message.setComments(this.commentService.getCommentsBatchByMessage(message.getId()));
        }
    }
    
    public Message getMessageById(Long id) {
        return this.messageRepository.getOne(id);
    }
    
    public Message saveNewMessage(Message message) {
        return this.messageRepository.save(message);
    }
    
    
}
