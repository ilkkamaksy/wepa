package projekti;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @GetMapping("/messages")
    public String getMessages(Model model, @RequestParam(defaultValue = "0") Integer page) {
        Account user = this.getCurrentUserAccount();
        
        Pageable pageable = PageRequest.of(page, 25, Sort.by("pub_date_time").descending());
        model.addAttribute("title", "Home");
        model.addAttribute("messages", this.messageRepository.findByAccountId(user.getId(), pageable));
        model.addAttribute("account", user);
        return "messageFeed";
    }
    
    @PostMapping("/messages")
    public String addMessage(@RequestParam String messageContent) {

        Account user = this.getCurrentUserAccount();
        
        Message message = new Message(messageContent, LocalDateTime.now(), user);
        this.messageRepository.save(message);
        
        return "redirect:/messages";
    }
    
    private Account getCurrentUserAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return this.accountRepository.findByUsername(username);
    }
    
}
