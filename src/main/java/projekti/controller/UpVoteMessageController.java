package projekti.controller;

import projekti.service.MessageService;
import projekti.service.UpVoteMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projekti.model.Message;
import projekti.model.UpVoteMessage;
import projekti.model.Account;
import projekti.service.AccountService;

@Controller
public class UpVoteMessageController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private UpVoteMessageService upVoteMessageService;
    
    @PostMapping("/messages/{id}/upvotes")
    public String upVoteMessage(@PathVariable Long id) {
        Account user = this.accountService.getCurrentUserAccount();
        Message message = this.messageService.getMessageById(id);
        
        UpVoteMessage upVote = new UpVoteMessage(user, message);
        this.upVoteMessageService.save(upVote);
        
        return "redirect:/feed";
    }
}
