package projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.model.UpVoteMessage;
import projekti.repository.UpVoteMessageRepository;

@Service
public class UpVoteMessageService {
    
    @Autowired
    private UpVoteMessageRepository upVoteMessageRepository;
    
    public UpVoteMessage save(UpVoteMessage upVote) {
        return this.upVoteMessageRepository.save(upVote);
    }
    
    public void delete(UpVoteMessage upVote) {
        this.upVoteMessageRepository.delete(upVote);
    }
    
}
