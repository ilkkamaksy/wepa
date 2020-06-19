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
public class SkillController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private SkillRatingRepository skillRatingRepository;

    @Transactional
    @PostMapping("/accounts/{accountSlug}/skills")
    public String addNewSkill(@PathVariable String accountSlug, @RequestParam String skillName) {

        Skill skill = skillRepository.findByName(skillName);
        if (skill == null) {
            skill = new Skill();
            skill.setName(skillName);
            skill = this.skillRepository.save(skill);
        }
        
        Account account = accountRepository.findBySlug(accountSlug);
        this.skillRatingRepository.save(new SkillRating(skill, account, 0));
        return "redirect:/accounts/" + accountSlug;
    }
    
    @PostMapping("/accounts/{accountSlug}/skills/{skillId}")
    public String addSkillRating(@PathVariable String accountSlug, @PathVariable Long skillId) {
        Account account = accountRepository.findBySlug(accountSlug);
        SkillRating skillRating = this.skillRatingRepository.findByAccountIdAndSkillId(account.getId(), skillId);
        skillRating.setRating(skillRating.getRating() + 1);
        
        this.skillRatingRepository.save(skillRating);
        
        return "redirect:/accounts/" + accountSlug;
    }
}
