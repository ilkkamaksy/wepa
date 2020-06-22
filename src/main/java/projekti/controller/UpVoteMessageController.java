package projekti.controller;

import java.util.concurrent.CompletableFuture;
import projekti.service.MessageService;
import projekti.service.UpVoteMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    @GetMapping("/messages/{id}/upvote")
    public @ResponseBody Boolean upVoteMessage(@PathVariable Long id) {
        Account user = this.accountService.getCurrentUserAccount();
        Message message = this.messageService.getMessageById(id);
        return this.upVoteMessageService.save(new UpVoteMessage(user, message));
    }
}
