package projekti.repository;

import java.util.List;
import projekti.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findByMessageId(Long messageId, Pageable pageable);
    
}
