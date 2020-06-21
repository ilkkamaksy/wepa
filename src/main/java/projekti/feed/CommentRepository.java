/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.feed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ilkka
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Query(value = "SELECT * FROM Comment WHERE message_id = ?1",
    countQuery = "SELECT count(*) FROM Comment WHERE message_id = ?1",
    nativeQuery = true)
    Page<Comment> findByMessageId(Long messageId, Pageable pageable);
}
