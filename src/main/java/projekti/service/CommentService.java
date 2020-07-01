package projekti.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projekti.model.Comment;
import projekti.repository.CommentRepository;
import projekti.model.Message;
import projekti.model.Account;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    public List<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }
   
    public List<Comment> getCommentsBatchByMessage(Long messageId) {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("pubDateTime").descending());
        List<Comment> comments = this.commentRepository.findByMessageId(messageId, pageable);
        Collections.reverse(comments);
        return comments;
    }
    
    public List<Comment> getCommentsBatchByMessage(Long messageId, Integer page) {
        Pageable pageable = PageRequest.of(page, 2, Sort.by("pubDateTime").descending());
        List<Comment> comments = this.commentRepository.findByMessageId(messageId, pageable);
        Collections.reverse(comments);
        return comments;
    }
    
    public Comment getCommentById(Long id) {
        return this.commentRepository.getOne(id);
    }
    
    public Comment saveComment(Comment comment) {
        return this.commentRepository.save(comment);
    }
}
