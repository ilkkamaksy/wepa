package projekti.repository;

import javax.transaction.Transactional;
import projekti.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Account findBySlug(String slug);
}