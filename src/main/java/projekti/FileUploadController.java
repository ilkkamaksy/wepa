package projekti;

import projekti.storage.StorageFileNotFoundException;
import projekti.storage.StorageService;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projekti.storage.*;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    ProfileImageRepository profileImageRepository;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/accounts/{slug}/images/upload")
    public String listUploadedFiles(Model model, @PathVariable String slug) throws IOException {
        model.addAttribute("account", this.accountRepository.findBySlug(slug));
        
        model.addAttribute("files", storageService.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(
                    FileUploadController.class,
                    "serveFile", 
                    path.getFileName()
                        .toString())
                        .build()
                        .toUri()
                        .toString())
            .collect(Collectors.toList()));

        return "uploadImage";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/accounts/{slug}/images")
    public String handleFileUpload(@PathVariable String slug, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        ProfileImage profileImage = new ProfileImage();
        profileImage.setImageUrl(file.getOriginalFilename());
        
        Account account = this.accountRepository.findBySlug(slug);
        account.setProfileImage(profileImage);
        this.accountRepository.save(account);
        
        storageService.store(file);
        
        redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/accounts/" + slug;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
            return ResponseEntity.notFound().build();
    }
}
