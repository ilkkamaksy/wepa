package projekti.controller;

import projekti.service.MessageService;
import projekti.service.CommentService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import projekti.model.Comment;
import projekti.model.Account;
import projekti.model.Message;
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
        model.addAttribute("currentUser", user);
        
        Comment comment = new Comment();
        comment.setContent(messageContent);
        comment.setPubDateTime(LocalDateTime.now());
        comment.setUser(user);
        comment.setMessage(this.messageService.getMessageById(id));
        
        this.commentService.saveComment(comment);
        
        model.addAttribute("item", comment);

        return new ModelAndView("fragments/feedPartials :: commentContainer");
    }
    
    @GetMapping("/messages/{id}/comments/page/{page}")
    public ModelAndView getMessageComments (
            Model model, 
            @PathVariable Long id, 
            @PathVariable Integer page
    ) {

        model.addAttribute("currentUser", this.accountService.getCurrentUserAccount());
        Message message = this.messageService.getMessageById(id);
        List<Comment> comments = this.commentService.getCommentsBatchByMessage(id, page);
        message.setComments(comments);
        model.addAttribute("message", message);
        
        return new ModelAndView("fragments/feedPartials :: ajaxCommentsContainer");
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
        model.addAttribute("currentUser", user);
        
        Comment comment = new Comment();
        comment.setContent(messageContent);
        comment.setPubDateTime(LocalDateTime.now());
        comment.setUser(user);
        comment.setParent(this.commentService.getCommentById(id));
        
        this.commentService.saveComment(comment);
        
        model.addAttribute("item", comment);

        return new ModelAndView("fragments/feedPartials :: commentContainer");
    }
}
