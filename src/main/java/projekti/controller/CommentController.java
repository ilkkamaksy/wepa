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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView addCommentToMessage(
            Model model, 
            @PathVariable Long id, 
            @RequestParam String messageContent,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String slug
    ) {
        Account user = this.accountService.getCurrentUserAccount();
        Comment comment = new Comment();
        comment.setContent(messageContent);
        comment.setPubDateTime(LocalDateTime.now());
        comment.setUser(user);
        comment.setMessage(this.messageService.getMessageById(id));
        
        this.commentService.saveComment(comment);
        
        return this.messageService.getMessageFeed(model, user, page, slug);
    }
    
    @PostMapping("/comments/{id}")
    public ModelAndView addCommentToComment(
            Model model, 
            @PathVariable Long id, 
            @RequestParam String messageContent,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String slug
    ) {
        Account user = this.accountService.getCurrentUserAccount();
        Comment comment = new Comment();
        comment.setContent(messageContent);
        comment.setPubDateTime(LocalDateTime.now());
        comment.setUser(user);
        comment.setParent(this.commentService.getCommentById(id));
        
        this.commentService.saveComment(comment);
        
        return this.messageService.getMessageFeed(model, user, page, slug);
    }
    
    
}
