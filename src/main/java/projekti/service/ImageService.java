package projekti.service;

import java.io.IOException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;;
import projekti.model.Account;
import projekti.model.ProfileImage;
import projekti.repository.ProfileImageRepository;

@Service
public class ImageService {
    
    @Autowired
    ProfileImageRepository profileImageRepository;
    
    @Autowired
    private AccountService accountService;

    public ProfileImage getImage(Long id) {
        return this.profileImageRepository.getOne(id);
    }

    @Transactional
    public ProfileImage save(MultipartFile file) throws IOException {
        ProfileImage img = new ProfileImage();
        img.setContent(file.getBytes());
        img.setMediaType(file.getContentType());
        img.setName(file.getOriginalFilename());
        img.setSize(file.getSize());
        img.setAccount(this.accountService.getCurrentUserAccount());
        ProfileImage result = this.profileImageRepository.save(img);
        
        Account user = this.accountService.getCurrentUserAccount();
        user.setProfileImage(img);
        this.accountService.saveUserAccount(user);
        
        return result;
    }
    
    public void remove(Long id) {
        this.profileImageRepository.deleteOne(id);
    }

}
