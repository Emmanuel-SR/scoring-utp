package services;

import daos.userDao;
import dtos.User;
import exceptions.IncorrectDaoOperation;
import java.util.List;
import java.util.Map;

public class UserService {

    private final userDao dao = new userDao();

    public UserService() {
    }

    public User findOne(long id, Map<String, String> result) {
        User usr = null;
        try {
            usr = dao.get(id);
            if (usr == null) {
                result.put("message", "No user found with id: " + id);
            }
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return usr;
    }

    public List<User> findAll(Map<String, String> result) {
        List<User> usuarios = null;
        try {
            usuarios = dao.all();
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return usuarios;
    }

    public User update(User usr, Map<String, String> result) {
        if (usr.getId() > 0) {
            try {
                if (dao.update(usr)) {
                    return usr;
                }
            } catch (IncorrectDaoOperation ex) {
                result.put("error", ex.getMessage());
            }
        }
        return null;
    }

}
