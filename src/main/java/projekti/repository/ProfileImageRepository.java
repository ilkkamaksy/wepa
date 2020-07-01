package projekti.repository;

import javax.transaction.Transactional;
import projekti.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
 
    @Modifying
    @Query("DELETE FROM ProfileImage p WHERE p.id = ?1")
    void deleteOne(Long entityId);
}
