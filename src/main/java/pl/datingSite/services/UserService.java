package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.logic.SmartFitAlgorithm;
import pl.datingSite.model.*;
import pl.datingSite.repository.RolesRepository;
import pl.datingSite.repository.UserRepository;
import pl.datingSite.tools.DistanceCalculator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private SmartFitAlgorithm smartFitAlgorithm;

    @Transactional
    public boolean setAdminRole() {
        Roles adminRole = new Roles("mati", "ADMIN");
        if(!rolesRepository.getRoles("mati").contains(adminRole)) {
            entityManager.merge(adminRole);
            return true;
        }
        return false;
    }

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
        User user = userRepository.getUserByUserName(username);
        if(user != null)
            return user;
        else
            throw new NoResultException("User not found");
    }

    public User getUserWithAllData(String username) {
        User user = userRepository.getUserByUserNameWithAppearanceAndCharacter(username);
        if(user != null)
            return user;
        else
            throw new NoResultException("User not found");
    }

    public Set<FoundUser> getUsers(SearchHelper searchHelper) {
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

        Set<User> result;
        if(searchHelper.getLookingFors() != null) {
             result = userRepository.getUsers(sex, dateFrom, dateTo, searchHelper.getMaritalStatuses(), searchHelper.getFigures(),
                    searchHelper.getHeightFrom(), searchHelper.getHeightTo(), searchHelper.getHairColors(), searchHelper.getSmokings(),
                    searchHelper.getAlcohol(), searchHelper.getChildren(), searchHelper.getLookingFors(), searchHelper.getReligions());
        } else {
            result = userRepository.getUsers(sex, dateFrom, dateTo, searchHelper.getMaritalStatuses(), searchHelper.getFigures(),
                    searchHelper.getHeightFrom(), searchHelper.getHeightTo(), searchHelper.getHairColors(), searchHelper.getSmokings(),
                    searchHelper.getAlcohol(), searchHelper.getChildren(), searchHelper.getReligions());
        }

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
                if(distanceCalculator.getDistance(searchHelper.getLocation(), tmp.getCity()) > searchHelper.getDistance())
                    iterator.remove();
            }
        }

        Set<FoundUser> foundUsers = new HashSet<>();
        iterator = result.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            FoundUser foundUser = new FoundUser(user.getAvatar(), user.getName(), user.getUsername(), user.getDateOfBirth(), user.getCity(), user.isFake());
            foundUsers.add(foundUser);
        }

        if(foundUsers != null)
            return foundUsers;
        else
            throw new NoResultException("Users not found");
    }

    public List<ClassifiedUser> getFitUsers(String username, boolean real) {
        User searchingUser = userRepository.getUserByUserName(username);
        List<User> userList = userRepository.findAll();

        List<ClassifiedUser> users = smartFitAlgorithm.getFittedUsers(searchingUser, userList, real);
        if (users != null)
            return users;
        else
            throw new NoResultException("Users not found");
    }

    public List<Roles> getRoles(String username) {
        return rolesRepository.getRoles(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(User user) {
        User updatedUser = entityManager.merge(user);
        if(updatedUser != null)
            return updatedUser;
        else
            throw new NoResultException("User not found");
    }

    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteAccount(username);
    }

}
