package projekti.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message extends AbstractPersistable<Long> {

    private String content;
    private LocalDateTime pubDateTime;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account user;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    @OrderBy("pub_date_time ASC")
    private List<Comment> comments;
   
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<UpVoteMessage> upVotes;
    
    @Formula("SELECT COUNT(c.id) FROM Comment c WHERE c.message_id = id")
    private Long commentCount;
   
}
