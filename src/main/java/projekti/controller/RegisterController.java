package projekti.controller;

import projekti.repository.AccountRepository;
import projekti.model.Account;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.model.Account;
import projekti.repository.AccountRepository;

@Controller
public class RegisterController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String getRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String addNewUser(@RequestParam String username, @RequestParam String password, @RequestParam String fullname, @RequestParam String slug) {
        if (accountRepository.findByUsername(username) != null || accountRepository.findBySlug(slug) != null) {
            return "redirect:/register";
        }
        
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setSlug(slug);
        account.setName(fullname);
        
        account.setAuthorities(Arrays.asList("USER"));
        accountRepository.save(account);
        
        return "redirect:/users/" + account.getSlug();
    }

}
