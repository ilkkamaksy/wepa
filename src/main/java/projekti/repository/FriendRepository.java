package projekti.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projekti.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    
    @Query(value = "SELECT * FROM Friend WHERE friendship_status = ?1",
    countQuery = "SELECT count(*) FROM Friend WHERE friendship_status = ?1",
    nativeQuery = true)
    List<Friend> findAllByFriendshipStatus(String status);
    
    @Query(value = "SELECT * FROM Friend WHERE account_id = ?1 OR friend_id = ?1",
    countQuery = "SELECT count(*) FROM Friend WHERE account_id = ?1 OR friend_id = ?1",
    nativeQuery = true)
    List<Friend> findAllByAccountId(Long accountId);
}
