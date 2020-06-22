package projekti.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.model.Comment;
import projekti.model.Message;
import projekti.model.Account;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpVoteComment extends AbstractPersistable<Long> {
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
    
}