package projekti.repository;

import javax.transaction.Transactional;
import projekti.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    
}
