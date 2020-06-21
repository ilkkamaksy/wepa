package projekti.skill;

import projekti.user.Account;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillRating extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    
    private Integer rating = 0;

}