package projekti.model;

import projekti.model.Message;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.model.Comment;
import projekti.model.ProfileImage;
import projekti.model.SkillRating;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractPersistable<Long> {

    private String username;
    private String password;
    private String slug;
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_image_id", referencedColumnName = "id")
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