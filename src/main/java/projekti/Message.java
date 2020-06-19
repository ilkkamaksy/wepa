package projekti;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
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
public class Message extends AbstractPersistable<Long> {
    
    private String content;
    private LocalDateTime pubDateTime;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account user;
}
