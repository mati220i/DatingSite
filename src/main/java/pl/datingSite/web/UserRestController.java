package pl.datingSite.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.datingSite.model.ClassifiedUser;
import pl.datingSite.model.FoundUser;
import pl.datingSite.model.SearchHelper;
import pl.datingSite.model.User;
import pl.datingSite.services.UserService;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

    final static Logger logger = Logger.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration/checkLogin/{login}", method = RequestMethod.GET)
    public ResponseEntity checkIfExistLogin(@PathVariable(value = "login") String login) {
        if(userService.checkIfExistLogin(login))
            return new ResponseEntity(true, HttpStatus.OK);
        else
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/registration/checkEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity checkIfExistEmail(@PathVariable(value = "email") String email) {
        if(userService.checkIfExistEmail(email))
            return new ResponseEntity(true, HttpStatus.OK);
        else
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/registration/addUser", method = RequestMethod.PUT)
    public ResponseEntity addUser(@RequestBody User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userService.addUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public ResponseEntity getUser(@RequestParam(value = "login") String login) {
        try {
            User user = userService.getUser(login);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getUserWithAllData", method = RequestMethod.POST)
    public ResponseEntity getUserWithAllData(@RequestParam(value = "login") String login) {
        try {
            User user = userService.getUserWithAllData(login);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            String password = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(password);
            User returnedUser = userService.updateUser(user);
            return new ResponseEntity(returnedUser, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@RequestBody User user) {
        try {
            String password = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(password);

            User returnedUser = userService.updateUser(user);
            return new ResponseEntity(returnedUser, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn("Change password failed: " + e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestBody String username) {
        userService.deleteUser(username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public ResponseEntity getUsers(@RequestBody SearchHelper searchHelper) {
        try {
            long millisActualTime = System.currentTimeMillis();

            Set<FoundUser> users = userService.getUsers(searchHelper);

            long executionTime = System.currentTimeMillis() - millisActualTime;
            System.out.println("Get Users: " + executionTime);

            return new ResponseEntity(users, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }


    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    public ResponseEntity getRole(@RequestParam("username") String username) {
        return new ResponseEntity(userService.getRoles(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFitUsers", method = RequestMethod.GET)
    public ResponseEntity getFitUsers(@RequestParam("username") String username, @RequestParam("real") boolean real) {
        try {
            long millisActualTime = System.currentTimeMillis();

            List<ClassifiedUser> users = userService.getFitUsers(username, real);

            long executionTime = System.currentTimeMillis() - millisActualTime;
            System.out.println("Get fit users: " + executionTime);

            return new ResponseEntity(users, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }




    @RequestMapping(value = "/setAdminRole", method = RequestMethod.GET)
    public ResponseEntity setAdminRole() {
        return new ResponseEntity(userService.setAdminRole(), HttpStatus.OK);
    }

}
