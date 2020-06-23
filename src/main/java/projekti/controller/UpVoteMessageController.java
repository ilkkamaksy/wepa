package projekti.controller;

import java.util.concurrent.CompletableFuture;
import projekti.service.MessageService;
import projekti.service.UpVoteMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView upVoteMessage(Model model, @PathVariable Long id) {
        Account user = this.accountService.getCurrentUserAccount();
        Message message = this.messageService.getMessageById(id);
        this.upVoteMessageService.save(new UpVoteMessage(user, message));

        Pageable pageable = PageRequest.of(0, 25, Sort.by("pubDateTime").descending());
        model.addAttribute("messages", this.messageService.getAllMessagesPaginated(pageable));
        return new ModelAndView("/fragments/feedPartials :: messageFeed");
    }
}
