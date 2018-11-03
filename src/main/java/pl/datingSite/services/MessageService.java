package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.model.User;
import pl.datingSite.model.messages.Conversation;
import pl.datingSite.model.messages.Message;
import pl.datingSite.model.messages.MessageHelper;
import pl.datingSite.repository.ConversationRepository;
import pl.datingSite.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Set<Conversation> getConversations(String username) {
        Set<Conversation> conversations = userRepository.getUserByUserName(username).getMessageBox().getConversationList();
        if(conversations != null)
            return conversations;
        else
            throw new NoResultException("Not found Conversations");
    }

    @Transactional
    public Set<Conversation> newMessage(MessageHelper messageHelper) {
        User sender = userRepository.getUserByUserName(messageHelper.getFrom());
        User receiver = userRepository.getUserByUserName(messageHelper.getTo());

        Conversation senderConversation = conversationRepository.getConversationWithUser(sender.getUsername(), receiver.getUsername());
        if(senderConversation == null)
            senderConversation = new Conversation(sender.getUsername(), receiver.getUsername());

        Conversation receiverConversation = conversationRepository.getConversationWithUser(receiver.getUsername(), sender.getUsername());
        if(receiverConversation == null)
            receiverConversation = new Conversation(receiver.getUsername(), sender.getUsername());

        Message message = messageHelper.getMessage();
        receiverConversation.getMessages().add(message);

        Message receiverMessage = new Message(message.getMessageFrom(), message.getMessage());
        receiverMessage.setWhenSent(message.getWhenSent());
        senderConversation.getMessages().add(receiverMessage);

        sender.getMessageBox().getConversationList().add(senderConversation);
        receiver.getMessageBox().getConversationList().add(receiverConversation);

        entityManager.persist(receiverConversation);
        entityManager.persist(senderConversation);


        entityManager.persist(message);
        entityManager.persist(receiverMessage);

        Set<Conversation> conversations = getConversations(sender.getUsername());
        if(conversations != null)
            return conversations;
        else
            throw new NoResultException("Conversations not found");
    }

    @Transactional
    public void readConversation(String whose, String fromWho) {
        Conversation conversationUserOpposite = conversationRepository.getConversationWithUser(fromWho, whose);
        List<Message> messages = conversationUserOpposite.getMessages();
        Iterator<Message> messageIterator = messages.iterator();

        while (messageIterator.hasNext()) {
            Message message = messageIterator.next();
            if(message.getMessageFrom().equals(conversationUserOpposite.getWhose()))
                message.setReaded(true);
        }

        Conversation userConversation = conversationRepository.getConversationWithUser(whose, fromWho);
        List<Message> userMessages = userConversation.getMessages();
        Iterator<Message> userMessagesIterator = userMessages.iterator();

        while (userMessagesIterator.hasNext()) {
            Message message = userMessagesIterator.next();
            if(message.getMessageFrom().equals(fromWho))
                message.setReaded(true);
        }
    }

}
