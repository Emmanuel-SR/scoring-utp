package services;

import daos.userDao;
import dtos.User;
import exceptions.DuplicateEntryDaoOperation;
import exceptions.IncorrectDaoOperation;
import java.util.List;
import java.util.Map;

public class UserService {

    private final userDao dao = new userDao();

    public UserService() {
    }

    public User changePassword(User usr, Map<String, String> result) {
        try {
            if (dao.changePassword(usr.getId(), util.Hash.hashSHA256(usr.getPassword()))) {
                return usr;
            }
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return null;
    }

    public User findOne(long id, Map<String, String> result) {
        User usr = null;
        try {
            usr = dao.get(id);
            if (usr == null) {
                result.put("warning", "No user found with id: " + id);
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
            } catch (IncorrectDaoOperation | DuplicateEntryDaoOperation ex) {
                result.put("error", ex.getMessage());
            }
        }
        return null;
    }

    public User create(User usr, Map<String, String> result) {
        usr.setPassword(util.Hash.hashSHA256(usr.getPassword()));
        usr.setAnswer(util.Hash.hashSHA256(usr.getAnswer()));

        if (!dao.exists(usr)) {
            try {
                if (dao.insert(usr)) {
                    return usr;
                }
            } catch (IncorrectDaoOperation ex) {
                result.put("error", ex.getMessage());
            }
        } else {
            result.put("warning", "User with username " + usr.getUsername() + " already exists");
        }
        return null;
    }

    public void delete(User usr, Map<String, String> result) {
        try {
            if (!dao.delete(usr.getId())) {
                result.put("warning", "No user found with id: " + usr.getId());
            }
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
    }

    public User findOneByEmail(String email, Map<String, String> result) {
        User usr = null;
        try {
            usr = dao.getByEmail(email);
            if (usr == null) {
                result.put("warning", "No user found with email: " + email);
            }
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return usr;
    }

    public boolean checkSecurityAnswer(String answer, Map<String, String> result) {
        String hashedAnswer = util.Hash.hashSHA256(answer);
        try {
            if (dao.checkSecurityAnswer(hashedAnswer)) {
                return true;
            } else {
                result.put("warning", "Incorrect answer.");
            }
        } catch (IncorrectDaoOperation ex) {
            result.put("error", ex.getMessage());
        }
        return false;
    }

}
