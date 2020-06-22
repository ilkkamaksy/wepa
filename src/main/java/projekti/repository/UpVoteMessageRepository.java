package projekti.repository;

import projekti.model.UpVoteMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpVoteMessageRepository extends JpaRepository<UpVoteMessage, Long> {
    
}
