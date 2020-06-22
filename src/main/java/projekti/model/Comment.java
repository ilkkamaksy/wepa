package projekti.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.model.Account;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends AbstractPersistable<Long> {

    private String content;
    private LocalDateTime pubDateTime;
    
    @ManyToOne
    private Comment parent;
    
    @OneToMany(mappedBy = "parent")
    private List<Comment> children;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<UpVoteComment> upVotes;
}