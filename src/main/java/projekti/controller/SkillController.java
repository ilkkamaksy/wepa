package projekti.controller;

import projekti.repository.AccountRepository;
import projekti.model.Account;
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
import projekti.service.AccountService;
import projekti.model.Skill;
import projekti.model.SkillRating;
import projekti.repository.SkillRatingRepository;
import projekti.repository.SkillRepository;

@Controller
public class SkillController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private SkillRatingRepository skillRatingRepository;

    @Transactional
    @PostMapping("/users/{userSlug}/skills")
    public String addNewSkill(@PathVariable String userSlug, @RequestParam String skillName) {

        Skill skill = skillRepository.findByName(skillName);
        if (skill == null) {
            skill = new Skill();
            skill.setName(skillName);
            skill = this.skillRepository.save(skill);
        }
        
        Account account = this.accountService.getAccountBySlug(userSlug);
        this.skillRatingRepository.save(new SkillRating(skill, account, 0));
        return "redirect:/users/" + userSlug;
    }
    
    @PostMapping("/users/{userSlug}/skills/{skillId}")
    public String addSkillRating(@PathVariable String userSlug, @PathVariable Long skillId) {
        Account user = this.accountService.getAccountBySlug(userSlug);
        
        SkillRating skillRating = this.skillRatingRepository.findByAccountIdAndSkillId(user.getId(), skillId);
        skillRating.setRating(skillRating.getRating() + 1);
        this.skillRatingRepository.save(skillRating);
        
        return "redirect:/users/" + userSlug;
    }
}
