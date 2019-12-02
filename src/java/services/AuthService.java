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

        String hashedPassword = util.Hash.hashSHA256(password);

        User usr = null;
        try {
            usr = dao.auth(username, hashedPassword);
            if (usr != null) {
            } else {
                response.put("warning", "Invalid username or password.");
            }
        } catch (IncorrectDaoOperation e) {
            response.put("error", e.getMessage());
        }
        return usr;
    }

}
