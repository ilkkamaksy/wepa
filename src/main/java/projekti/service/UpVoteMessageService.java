package projekti.service;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import projekti.model.Account;
import projekti.model.Message;
import projekti.model.UpVoteMessage;
import projekti.repository.UpVoteMessageRepository;

@Service
public class UpVoteMessageService {
    
    @Autowired
    private UpVoteMessageRepository upVoteMessageRepository;
    
    public Boolean save(UpVoteMessage upVoteMessage) {
        if (null != this.upVoteMessageRepository.save(upVoteMessage)) {
            return true;
        } 
        return false;
    }
    
    public void delete(UpVoteMessage upVote) {
        this.upVoteMessageRepository.delete(upVote);
    }
    
}
