/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.feed;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.user.Account;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends AbstractPersistable<Long> {

    private String content;
    private LocalDateTime pubDateTime;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;
    
}