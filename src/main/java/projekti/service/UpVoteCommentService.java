package projekti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.model.UpVoteComment;
import projekti.repository.UpVoteCommentRepository;

@Service
public class UpVoteCommentService {
    
    @Autowired
    private UpVoteCommentRepository upVoteCommentRepository;
    
    public UpVoteComment save(UpVoteComment upVote) {
        return this.upVoteCommentRepository.save(upVote);
    }
    
    public void delete(UpVoteComment upVote) {
        this.upVoteCommentRepository.delete(upVote);
    }
    
}
