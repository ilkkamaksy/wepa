package projekti.controller;

import projekti.service.MessageService;
import projekti.service.UpVoteMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView upVoteMessage(
            Model model, 
            @PathVariable Long id, 
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String slug
    ) {
        Account user = this.accountService.getCurrentUserAccount();
        model.addAttribute("currentUser", user);
        
        Message message = this.messageService.getMessageById(id);
        this.upVoteMessageService.save(new UpVoteMessage(user, message));

        model.addAttribute("item", message);

        return new ModelAndView("fragments/feedPartials :: messageNav");
    }
}
