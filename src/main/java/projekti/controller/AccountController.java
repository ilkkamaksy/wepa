package projekti.controller;

import projekti.service.AccountService;
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
import projekti.service.MessageService;
import projekti.repository.SkillRatingRepository;
import projekti.repository.SkillRepository;
import projekti.model.Account;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/users/{slug}")
    public String getUserAccount(Model model, @PathVariable String slug, @RequestParam(defaultValue = "0") Integer page) {
        Account user = this.accountService.getAccountBySlug(slug);
        model.addAttribute("user", user);
        
        model.addAttribute("currentUser", this.accountService.getCurrentUserAccount());
        
        Pageable pageable = PageRequest.of(page, 25, Sort.by("pub_date_time").descending());
        model.addAttribute("messages", this.messageService.getMessagesByUserPaginated(user, pageable));
        
        return "profile";
    }
}