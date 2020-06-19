package projekti;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRatingRepository extends JpaRepository<SkillRating, Long> {
    List<SkillRating> findBySkillId(Long skillId);
    List<SkillRating> findByAccountId(Long accountId);
    SkillRating findByAccountIdAndSkillId(Long accountId, Long skillId);
    
}
