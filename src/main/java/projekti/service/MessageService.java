package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projekti.model.Message;
import projekti.repository.MessageRepository;
import projekti.model.Account;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }
    
    public Page<Message> getAllMessagesPaginated(Pageable pageable) {
        return this.messageRepository.findAll(pageable);
    }
    
    public Page<Message> getMessagesByUserPaginated(Account user, Pageable pageable) {
        return this.messageRepository.findByAccountId(user.getId(), pageable);
    }
    
    public Message getMessageById(Long id) {
        return this.messageRepository.getOne(id);
    }
    
    public Message saveNewMessage(Message message) {
        return this.messageRepository.save(message);
    }
}
