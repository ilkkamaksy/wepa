package projekti;

import java.util.Arrays;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private SkillRatingRepository skillRatingRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/accounts")
    public String list(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "accounts";
    }
    
    @GetMapping("/accounts/{slug}")
    public String getUserAccount(Model model, @PathVariable String slug) {
        model.addAttribute("account", accountRepository.findBySlug(slug));
        return "profile";
    }

}
