package services;

import daos.userDao;
import dtos.User;
import exceptions.IncorrectDaoOperation;
import java.util.Map;

public class AuthService {

    private final userDao dao = new userDao();

    public AuthService() {
    }

    public User authenticate(String username, String password, Map<String, String> response) {
        User usr = null;
        try {
            usr = dao.auth(username, password);
            if (usr != null) {
            } else {
                response.put("message", "Invalid username or password.");
            }
        } catch (IncorrectDaoOperation e) {
            response.put("error", e.getMessage());
        }
        return usr;
    }

}
