package auth;

import dtos.User;
import java.util.HashMap;
import java.util.Map;
import services.AuthService;
import util.Assert;

public class AuthTest {

    private static final AuthService SERVICE = new AuthService();

    public static void main(String[] args) {
        authenticate();
    }

    private static void authenticate() {
        Map<String, String> result = new HashMap<>();
        String username = "1626989";
        String password = "abc.123";
        User usr = SERVICE.authenticate(username, password, result);
        Assert.notNull(usr, result.get("error"));
    }

}
