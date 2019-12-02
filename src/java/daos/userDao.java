package daos;

import dtos.User;
import exceptions.DuplicateEntryDaoOperation;
import exceptions.IncorrectDaoOperation;
import interfaces.ICrudDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao extends Dao implements ICrudDao<User, Long> {

    private static final String SQL_AUTH = "select * from users where username=? and password=?";
    private static final String SQL_INSERT = "insert into users (username, password, email, first_name, last_name,security_id, question_answer) values(?,?,?,?,?,?,?)";
    private static final String SQL_EXIST_BY_USERNAME = "select 1 from users where username=?";
    private static final String SQL_GET_BY_ID = "select * from users where user_id=?";
    private static final String SQL_GET_BY_EMAIL = "select * from users where email=?";
    private static final String SQL_ALL = "select user_id, username, email, first_name, last_name from users";
    private static final String SQL_UPDATE = "update users set username=?, email=?, first_name=?, last_name=? where user_id=?";
    private static final String SQL_CHANGE_PASSWORD = "update users set password=? where user_id=?";
    private static final String SQL_DELETE = "delete from users where user_id=?";
    private static final String SQL_CHECK_SECURITY_ANSWER = "select 1 from users where question_answer=?";

    public User auth(String username, String contrasena) {
        User usr = null;
        try {
            ps = cnn.getConnection().prepareStatement(SQL_AUTH);

            ps.setString(1, username);
            ps.setString(2, contrasena);

            res = ps.executeQuery();

            while (res.next()) {
                usr = new User(res.getLong("user_id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("email"));
                usr.setSecurityQuestionId(res.getLong("security_id"));
                usr.setAnswer(res.getString("question_answer"));
            }

        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }

        return usr;
    }

    public boolean changePassword(long userId, String password) {
        try {
            ps = cnn.getConnection().prepareStatement(SQL_CHANGE_PASSWORD);

            ps.setString(1, password);
            ps.setLong(2, userId);

            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new IncorrectDaoOperation(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean insert(User usr) {
        try {
            ps = cnn.getConnection().prepareStatement(SQL_INSERT);

            ps.setString(1, usr.getUsername());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getEmail());
            ps.setString(4, usr.getFirstname());
            ps.setString(5, usr.getLastname());
            ps.setLong(6, usr.getSecurityQuestionId());
            ps.setString(7, usr.getAnswer());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new IncorrectDaoOperation(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(User usr) {
        try {
            if (usr.getId() > 0) {

                ps = cnn.getConnection().prepareStatement(SQL_UPDATE);
                ps.setString(1, usr.getUsername());
                ps.setString(2, usr.getEmail());
                ps.setString(3, usr.getFirstname());
                ps.setString(4, usr.getLastname());
                ps.setLong(5, usr.getId());

                if (ps.executeUpdate() > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_DUPLICATE_PK) {
                throw new DuplicateEntryDaoOperation(e.getMessage());
            } else {
                throw new IncorrectDaoOperation(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public User get(Long id) {
        User usr = null;
        try {
            ps = cnn.getConnection().prepareStatement(SQL_GET_BY_ID);
            ps.setLong(1, id);
            res = ps.executeQuery();
            while (res.next()) {
                usr = new User(res.getLong("user_id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("email"));
                usr.setSecurityQuestionId(res.getLong("security_id"));
                usr.setAnswer(res.getString("question_answer"));
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }
        return usr;
    }

    @Override
    public List<User> all() {
        List<User> users = new ArrayList<>();
        try {
            ps = cnn.getConnection().prepareStatement(SQL_ALL);
            res = ps.executeQuery();
            while (res.next()) {
                User usr = new User(res.getLong("user_id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("email"));

                users.add(usr);
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }
        return users;
    }

    @Override
    public boolean delete(Long userId) {
        try {
            ps = cnn.getConnection().prepareStatement(SQL_DELETE);
            ps.setLong(1, userId);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new IncorrectDaoOperation(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean exists(User usr) {
        try {
            ps = cnn.getConnection().prepareStatement(SQL_EXIST_BY_USERNAME);
            ps.setString(1, usr.getUsername());
            res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new IncorrectDaoOperation(e.getMessage());
        }
        return false;
    }

    public User getByEmail(String email) {
        User usr = null;
        try {
            ps = cnn.getConnection().prepareStatement(SQL_GET_BY_EMAIL);
            ps.setString(1, email);
            res = ps.executeQuery();
            while (res.next()) {
                usr = new User(res.getLong("user_id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("email"));
                usr.setSecurityQuestionId(res.getLong("security_id"));
                usr.setAnswer(res.getString("question_answer"));
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }
        return usr;
    }

    public boolean checkSecurityAnswer(String hashedAnswer) {
        try {
            ps = cnn.getConnection().prepareStatement(SQL_CHECK_SECURITY_ANSWER);
            ps.setString(1, hashedAnswer);
            res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new IncorrectDaoOperation(e.getMessage());
        }
        return false;
    }
}
