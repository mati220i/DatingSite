package pl.datingSite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.datingSite.model.Roles;
import pl.datingSite.model.User;
import pl.datingSite.repository.RolesRepository;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class Starter implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }


    /********* drop db *************/
    /*
    drop table activation_code cascade constraint;
drop table appearance cascade constraint;
drop table friends cascade constraint;
drop table friends_friends_usernames cascade constraint;
drop table friends_invitations_from cascade constraint;
drop table friends_send_invitations cascade constraint;
drop table holiday cascade constraint;
drop table looking_for cascade constraint;
drop table movie_type cascade constraint;
drop table notification cascade constraint;
drop table roles cascade constraint;
drop table style cascade constraint;
drop table user_interests cascade constraint;
drop table users cascade constraint;
drop table conversation_messages cascade constraint;
drop table message_box cascade constraint;
drop table message_box_conversation_list cascade constraint;
drop table conversation cascade constraint;
drop table message cascade constraint;



     */

}
