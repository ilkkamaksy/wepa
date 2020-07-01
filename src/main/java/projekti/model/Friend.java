package projekti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account user;
    
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Account friend;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendshipStatus friendshipStatus;
    
}
