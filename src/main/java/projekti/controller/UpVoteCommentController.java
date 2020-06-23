package projekti.controller;
        
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
import projekti.model.Comment;
import projekti.model.UpVoteComment;
import projekti.service.CommentService;
import projekti.service.UpVoteCommentService;
import projekti.model.Account;
import projekti.model.Message;
import projekti.model.UpVoteMessage;
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
    public ModelAndView upVoteComment(Model model, @PathVariable Long id) {
        Account user = this.accountService.getCurrentUserAccount();
        Comment comment = this.commentService.getCommentById(id);
        this.upVoteCommentService.save(new UpVoteComment(user, comment));

        Pageable pageable = PageRequest.of(0, 25, Sort.by("pubDateTime").descending());
        model.addAttribute("messages", this.messageService.getAllMessagesPaginated(pageable));
        return new ModelAndView("/fragments/feedPartials :: messageFeed");
    }
}
