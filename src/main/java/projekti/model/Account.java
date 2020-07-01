package projekti.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import org.springframework.lang.Nullable;
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

    @Nullable
    @OneToOne(mappedBy="account", cascade = CascadeType.ALL)
    private ProfileImage profileImage;
    
    @OneToMany(mappedBy = "account")
    @OrderBy("rating DESC")
    private List<SkillRating> skillRatings;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> messages;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "user")
    private List<Friend> friends;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities;

}