package projekti.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projekti.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    
    List<Friend> findByFriendshipStatus(String status);

    List<Friend> findByUserId(Long id);
    
}
