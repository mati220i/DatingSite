package pl.datingSite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.datingSite.model.Roles;
import pl.datingSite.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class Starter implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        String password = new BCryptPasswordEncoder().encode("mati");
//        User user = new User("mati", password, "mati", "Mateusz", "Śliwa", "Pińczów", 23);
//        Roles role = new Roles("mati", "user");
//        entityManager.persist(user);
//        entityManager.persist(role);
    }
}
