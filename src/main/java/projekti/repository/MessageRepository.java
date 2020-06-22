package projekti.repository;

import projekti.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
    @Query(value = "SELECT * FROM Message msg "
            + "WHERE msg.account_id = ?1",
    countQuery = "SELECT count(*) FROM Message WHERE account_id = ?1",
    nativeQuery = true)
    Page<Message> findByAccountId(Long accountId, Pageable pageable);
}
