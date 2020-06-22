package projekti.controller;

import projekti.service.MessageService;
import projekti.service.CommentService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.model.Comment;
import projekti.model.Account;
import projekti.service.AccountService;

@Controller
public class CommentController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private CommentService commentService;
    
    @PostMapping("/messages/{id}/comment")
    public String addCommentToMessage(@PathVariable Long id, @RequestParam String messageContent) {
        Account user = this.accountService.getCurrentUserAccount();
        Comment comment = new Comment();
        comment.setContent(messageContent);
        comment.setPubDateTime(LocalDateTime.now());
        comment.setUser(user);
        comment.setMessage(this.messageService.getMessageById(id));
        
        this.commentService.saveComment(comment);
        
        return "redirect:/feed";
    }
    
    @PostMapping("/comments/{id}")
    public String addCommentToComment(@PathVariable Long id, @RequestParam String messageContent) {
        Account user = this.accountService.getCurrentUserAccount();
        Comment comment = new Comment();
        comment.setContent(messageContent);
        comment.setPubDateTime(LocalDateTime.now());
        comment.setUser(user);
        comment.setParent(this.commentService.getCommentById(id));
        
        this.commentService.saveComment(comment);
        
        return "redirect:/feed";
    }
}
