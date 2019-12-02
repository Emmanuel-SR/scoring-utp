package user;

import dtos.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import services.UserService;
import util.Assert;

public class UserTest {

    private static final UserService SERVICE = new UserService();

    public static void main(String[] args) {
        //findOne();
        //findAll();
        //update();
        create();
        //changePassword();
        //delete();
    }

    private static void findOne() {
        Map<String, String> result = new HashMap<>();
        long id = 0;
        User user = SERVICE.findOne(id, result);
        Assert.notNull(user, result.get("error"));
    }

    private static void findAll() {
        Map<String, String> errors = new HashMap<>();
        List<User> users = SERVICE.findAll(errors);
        Assert.notNull(users, errors.get("error"));
    }

    private static void update() {
        Map<String, String> error = new HashMap<>();
        User usr = new User(1, "1626989", "abc.123", "JOSE EMMANUEL", "SIRVAS RUEDA", "1626989@utp.edu.pe");
        User result = SERVICE.update(usr, error);
        Assert.notNull(result, error.get("error"));
    }

    private static void create() {
        Map<String, String> result = new HashMap<>();
        User usr = new User("1624655", "abc.123", "VANDERLEI ELIEZER", "PEREZ CISNEROS", "1624655@utp.edu.pe");
        usr.setSecurityQuestionId(3);
        usr.setAnswer("Perez");
        User newUser = SERVICE.create(usr, result);
        Assert.notNull(newUser, result.get("error"));
    }

    private static void changePassword() {
        Map<String, String> error = new HashMap<>();
        User usr = new User();
        usr.setId(2L);
        usr.setPassword("abc.123");
        User result = SERVICE.changePassword(usr, error);
        Assert.notNull(result, error.get("error"));
    }

    private static void delete() {
        Map<String, String> error = new HashMap<>();
        User usr = new User();
        usr.setId(3L);
        SERVICE.delete(usr, error);
    }

}
