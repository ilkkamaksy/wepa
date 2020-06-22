package projekti.controller;

import projekti.service.MessageService;
import projekti.service.CommentService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.model.Message;
import projekti.model.Account;
import projekti.service.AccountService;

@Controller
public class MessageController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/feed")
    public String getMessages(Model model, @RequestParam(defaultValue = "0") Integer page) {
        model.addAttribute("title", "Feed");
        model.addAttribute("user", this.accountService.getCurrentUserAccount());
        model.addAttribute("currentUser", this.accountService.getCurrentUserAccount());
        
        Pageable pageable = PageRequest.of(page, 25, Sort.by("pubDateTime").descending());
        model.addAttribute("messages", this.messageService.getAllMessagesPaginated(pageable));
        return "feed";
    }

    @PostMapping("/messages")
    public String addMessage(@RequestParam String messageContent) {
        Account user = this.accountService.getCurrentUserAccount();
        Message message = new Message();
        message.setContent(messageContent);
        message.setPubDateTime(LocalDateTime.now());
        message.setUser(user);
        this.messageService.saveNewMessage(message);
        
        return "redirect:/feed";
    }

}
