/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projekti.skill.SkillRatingRepository;
import projekti.skill.SkillRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private SkillRatingRepository skillRatingRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    public Account getAccountBySlug(String slug) {
        return accountRepository.findBySlug(slug);
    }
    
    public Account getCurrentUserAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return this.accountRepository.findByUsername(username);
    }
    
    public Account saveUserAccount(Account user) {
        return this.accountRepository.save(user);
    }
}
