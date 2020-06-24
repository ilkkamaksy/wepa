package projekti.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImage extends AbstractPersistable<Long> {
    
    	@Lob
        @Basic(fetch = FetchType.LAZY)
        private byte[] content;

        private String name;

        private String mediaType;

        private Long size;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "account_id")
        private Account account;

}
