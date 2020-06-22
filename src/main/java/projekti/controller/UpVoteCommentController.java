package projekti.controller;
        
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import projekti.model.Comment;
import projekti.model.UpVoteComment;
import projekti.service.CommentService;
import projekti.service.UpVoteCommentService;
import projekti.model.Account;
import projekti.service.AccountService;

@Controller
public class UpVoteCommentController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private UpVoteCommentService upVoteCommentService;
    
    @GetMapping("/comments/{id}/upvote")
    public @ResponseBody Boolean upVoteComment(@PathVariable Long id) {
        Account user = this.accountService.getCurrentUserAccount();
        Comment comment = this.commentService.getCommentById(id);
        UpVoteComment upVote = new UpVoteComment(user, comment);
        return this.upVoteCommentService.save(upVote);
    }
}
