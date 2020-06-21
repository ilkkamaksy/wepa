package projekti.feed;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projekti.user.Account;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    public List<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }
    
    public Page<Comment> getAllCommentsPaginated(Pageable pageable) {
        return this.commentRepository.findAll(pageable);
    }
    
    public Page<Comment> getCommentsByMessagePaginated(Message message, Pageable pageable) {
        return this.commentRepository.findByMessageId(message.getId(), pageable);
    }
    
    public Comment getCommentById(Long id) {
        return this.commentRepository.getOne(id);
    }
    
    public Comment saveComment(Comment comment) {
        return this.commentRepository.save(comment);
    }
}
