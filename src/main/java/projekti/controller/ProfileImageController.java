package projekti.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import projekti.model.ProfileImage;

import projekti.service.AccountService;
import projekti.service.ImageService;

@Controller
public class ProfileImageController {
    
    @Autowired
    private AccountService accountService;
   
    @Autowired
    private ImageService imageService;
    
    @GetMapping("/users/{slug}/images/upload")
    public String showUploadForm(Model model, @PathVariable String slug) throws IOException {
        model.addAttribute("currentUser", this.accountService.getCurrentUserAccount());
        return "uploadImage";
    }

    @PostMapping("/users/{slug}/images")
    public String uploadFile(@PathVariable String slug, @RequestParam("file") MultipartFile file) {

        try {
            this.imageService.save(file);    
        } catch (IOException ex) {
            
        }
        
        return "redirect:/users/" + slug;
    }
    
    @GetMapping(path = "/images/{id}", produces = "image/jpeg")
    public @ResponseBody byte[] viewFile(@PathVariable Long id) throws IOException {
        ProfileImage img = this.imageService.getImage(id);
        return img.getContent();
    }
    
    @GetMapping("/users/{slug}/images/remove/{id}")
    public String removeImage(@PathVariable String slug, @PathVariable Long id) throws IOException {
        this.imageService.remove(id);
        return "redirect:/users/" + slug;
    }

}
