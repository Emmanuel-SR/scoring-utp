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
    }

    private static void findOne() {
        Map<String, String> result = new HashMap<>();
        long id = 0;
        User user = SERVICE.findOne(id, result);
        Assert.notNull(user, result.get("message"));
    }

    private static void findAll() {
        Map<String, String> errors = new HashMap<>();
        List<User> users = SERVICE.findAll(errors);
        Assert.notEmpty(users, errors.get("message"));
    }
    
      private static void update() {
        Map<String, String> error = new HashMap<>();
        User usr = new User(1,"1626989","abc.123","emmanuel","sr","1626989@utp.edu.pe","student");
        User result = SERVICE.update(usr, error);
        Assert.notNull(result, error.get("error"));
    }


}
