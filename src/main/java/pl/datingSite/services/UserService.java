package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Service;
import pl.datingSite.model.Roles;
import pl.datingSite.model.SearchHelper;
import pl.datingSite.model.User;
import pl.datingSite.repository.UserRepository;
import pl.datingSite.tools.DistanceCalculator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    public boolean checkIfExistLogin(String login) {
        String log = userRepository.checkIfExistLogin(login);
        if(log != null)
            return true;
        else
            return false;
    }

    public boolean checkIfExistEmail(String email) {
        String emailFromDB = userRepository.checkIfExistEmail(email);
        if(emailFromDB != null)
            return true;
        else
            return false;
    }

    @Transactional
    public void addUser(User user) {
        Roles role = new Roles(user.getUsername(), "USER");
        entityManager.persist(user);
        entityManager.persist(role);
        notificationService.sendFirstNotification(user);
    }

    public User getUser(String username) {
//        List<User> userList = userRepository.findAll();
//        Iterator<User> iterator = userList.iterator();
//
//        while (iterator.hasNext()) {
//            User tmp = iterator.next();
//
//            if(tmp.getUsername().equals(username))
//                return tmp;
//        }

        return userRepository.getUserByUserName(username);
    }

    public Set<User> getUsers(SearchHelper searchHelper) {
        String sex = "";
        if(searchHelper.getSex() != null && searchHelper.getSex().equals("Kobiety"))
            sex = "Kobieta";
        if(searchHelper.getSex() != null && searchHelper.getSex().equals("Mężczyźni"))
            sex = "Mężczyzna";

        Date dateFrom = null, dateTo = null;
        if(searchHelper.getAgeFrom() != null) {
            LocalDate now = LocalDate.now();
            Integer yearFrom = now.getYear() - searchHelper.getAgeFrom();
            dateFrom = new GregorianCalendar(yearFrom, now.getMonthValue(), now.getDayOfMonth()).getTime();

        }
        if(searchHelper.getAgeTo() != null) {
            LocalDate now = LocalDate.now();
            Integer yearTo = now.getYear() - searchHelper.getAgeTo();
            dateTo = new GregorianCalendar(yearTo, now.getMonthValue(), now.getDayOfMonth()).getTime();
        }

        Set<User> result = userRepository.getUsers(sex, dateFrom, dateTo, searchHelper.getMaritalStatuses(), searchHelper.getFigures(),
                searchHelper.getHeightFrom(), searchHelper.getHeightTo(), searchHelper.getHairColors(), searchHelper.getSmokings(),
                searchHelper.getAlcohol(), searchHelper.getChildren(), searchHelper.getLookingFors());
        Iterator<User> iterator = result.iterator();

        if(searchHelper.isWithAvatar()) {
            while (iterator.hasNext()) {
                User tmp = iterator.next();
                if(tmp.getAvatar() == null)
                    iterator.remove();
            }
        }
        iterator = result.iterator();
        if(searchHelper.isReal()) {
            while (iterator.hasNext()) {
                User tmp = iterator.next();
                if(tmp.isFake())
                    iterator.remove();
            }
        }
        if(searchHelper.getDistance() != null) {
            DistanceCalculator distanceCalculator = new DistanceCalculator();
            iterator = result.iterator();

            while (iterator.hasNext()) {
                User tmp = iterator.next();
                System.out.println(distanceCalculator.getDistance(searchHelper.getLocation(), tmp.getCity()) + " > " + searchHelper.getDistance());
                if(distanceCalculator.getDistance(searchHelper.getLocation(), tmp.getCity()) > searchHelper.getDistance())
                    iterator.remove();
            }
        }

        return result;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteAccount(username);
    }

}
