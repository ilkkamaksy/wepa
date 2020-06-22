package projekti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.model.Account;
import projekti.service.AccountService;

@Controller
public class PageController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping("/")
    public String home(Model model) {
        Account user = this.accountService.getCurrentUserAccount();
        if (user != null) {
            return "redirect:/feed";
        }
        return "index";
    }
}
