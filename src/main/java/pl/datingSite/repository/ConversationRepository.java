package pl.datingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.datingSite.model.messages.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("select c from Conversation c where c.whose = :whose and c.fromWho = :fromWho")
    public Conversation getConversationWithUser(@Param("whose") String whose, @Param("fromWho") String fromWho);

}
