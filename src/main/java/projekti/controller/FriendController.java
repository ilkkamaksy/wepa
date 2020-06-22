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
import projekti.model.Friend;
import projekti.model.FriendshipStatus;
import projekti.repository.FriendRepository;

@Controller
public class FriendController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private FriendRepository friendRepository;

    @GetMapping("/users/{slug}/friends")
    public String acceptFriendRequest(Model model, @PathVariable String slug) {
        Account currentUser = this.accountService.getCurrentUserAccount();
        model.addAttribute("friends", this.friendRepository.findAllByAccountId(currentUser.getId()));
        model.addAttribute("currentUser", currentUser);
        
        return "friends";
    }
    
    @GetMapping("/users/{slug}/friend-request")
    public String sendFriendRequest(@PathVariable String slug) {
        this.friendRepository.save(new Friend(this.accountService.getCurrentUserAccount(), this.accountService.getAccountBySlug(slug), FriendshipStatus.PENDING));
        
        return "redirect:/users/" + slug;
    }
    
    @GetMapping("/users/{slug}/friend-request/{id}/accept")
    public String acceptFriendRequest(@PathVariable String slug, @PathVariable Long id) {
        Friend friend = this.friendRepository.getOne(id);
        friend.setFriendshipStatus(FriendshipStatus.ACCEPTED);
        this.friendRepository.save(friend);
        
        return "redirect:/users/" + slug;
    }
    
    @GetMapping("/users/{slug}/friend-request/{id}/reject")
    public String rejectFriendRequest(@PathVariable String slug, @PathVariable Long id) {
        Friend friend = this.friendRepository.getOne(id);
        this.friendRepository.delete(friend);

        return "redirect:/users/" + slug + "/friends";
    }
}
