package projekti.user;

import java.util.Arrays;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.feed.MessageService;
import projekti.skill.SkillRatingRepository;
import projekti.skill.SkillRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("accounts", this.accountService.getAllAccounts());
        return "users";
    }
    
    @GetMapping("/users/{slug}")
    public String getUserAccount(Model model, @PathVariable String slug, @RequestParam(defaultValue = "0") Integer page) {
        Account user = this.accountService.getAccountBySlug(slug);
        model.addAttribute("user", user);
        
        Pageable pageable = PageRequest.of(page, 25, Sort.by("pub_date_time").descending());
        model.addAttribute("messages", this.messageService.getMessagesByUserPaginated(user, pageable));
        
        return "profile";
    }
}
