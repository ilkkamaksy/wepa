package projekti;

import java.util.HashMap;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Account extends AbstractPersistable<Long> {

    private String username;
    private String password;
    private String slug;
    private String name;
    
    @OneToMany(mappedBy = "account")
    private List<SkillRating> skillRatings;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities;

}