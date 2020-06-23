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
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView addNewSkill(Model model, @PathVariable String userSlug, @RequestParam String skillName) {

        Skill skill = skillRepository.findByName(skillName);
        if (skill == null) {
            skill = new Skill();
            skill.setName(skillName);
            skill = this.skillRepository.save(skill);
        }
        
        Account user = this.accountService.getAccountBySlug(userSlug);
        this.skillRatingRepository.save(new SkillRating(skill, user, 0));

        model.addAttribute("user", user);
        model.addAttribute("currentUser", this.accountService.getCurrentUserAccount());
        
        return new ModelAndView("/fragments/skillsList :: skillsList");
    }
    
    @GetMapping("/users/{userSlug}/skills/{skillId}")
    public ModelAndView addSkillRating(Model model, @PathVariable String userSlug, @PathVariable Long skillId) {
        Account user = this.accountService.getAccountBySlug(userSlug);
        
        SkillRating skillRating = this.skillRatingRepository.findByAccountIdAndSkillId(user.getId(), skillId);
        skillRating.setRating(skillRating.getRating() + 1);
        this.skillRatingRepository.save(skillRating);
        
        model.addAttribute("user", user);
        model.addAttribute("currentUser", this.accountService.getCurrentUserAccount());
        
        return new ModelAndView("/fragments/skillsList :: skillsList");
    }
}
