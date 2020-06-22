package projekti.repository;

import projekti.model.UpVoteComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpVoteCommentRepository extends JpaRepository<UpVoteComment, Long> {
    
}
