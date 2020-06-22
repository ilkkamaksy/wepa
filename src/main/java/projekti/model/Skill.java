package projekti.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill extends AbstractPersistable<Long> {

    private String name;
    
    @OneToMany(mappedBy = "skill")
    List<SkillRating> skillRatings;

}
