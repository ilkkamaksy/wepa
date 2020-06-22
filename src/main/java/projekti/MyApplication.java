package projekti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import projekti.storage.StorageProperties;
import projekti.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
    }
    
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
                storageService.init();
        };
    }
}
