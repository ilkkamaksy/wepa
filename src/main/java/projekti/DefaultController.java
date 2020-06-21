package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.user.Account;
import projekti.user.AccountService;

@Controller
public class DefaultController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping("/")
    public String home(Model model) {
//        Account user = this.accountService.getCurrentUserAccount();
//        if (user != null) {
//            return "redirect:/feed";
//        }
        
        model.addAttribute("message", "World!");
        return "index";
    }
}
