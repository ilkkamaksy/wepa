package projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.model.UpVoteComment;
import projekti.repository.UpVoteCommentRepository;

@Service
public class UpVoteCommentService {
    
    @Autowired
    private UpVoteCommentRepository upVoteCommentRepository;
    
    public Boolean save(UpVoteComment upVoteComment) {
        if (null != this.upVoteCommentRepository.save(upVoteComment)) {
            return true;
        } 
        return false;
    }
    
    public void delete(UpVoteComment upVote) {
        this.upVoteCommentRepository.delete(upVote);
    }
    
}
