package projekti.controller;
        
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import projekti.model.Comment;
import projekti.model.UpVoteComment;
import projekti.service.CommentService;
import projekti.service.UpVoteCommentService;
import projekti.model.Account;
import projekti.service.AccountService;
import projekti.service.MessageService;

@Controller
public class UpVoteCommentController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private UpVoteCommentService upVoteCommentService;
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/comments/{id}/upvote")
    public ModelAndView upVoteComment(
            Model model, 
            @PathVariable Long id, 
            @RequestParam(defaultValue = "") String slug
    ) {
        Account user = this.accountService.getCurrentUserAccount();
        model.addAttribute("currentUser", user);
        
        Comment comment = this.commentService.getCommentById(id);
        UpVoteComment upVote = new UpVoteComment(user, comment);
        this.upVoteCommentService.save(upVote);

        model.addAttribute("item", comment);

        return new ModelAndView("fragments/feedPartials :: messageNav");
    }
}
