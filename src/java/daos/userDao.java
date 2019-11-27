package daos;

import static daos.Dao.ps;
import static daos.Dao.res;
import dtos.User;
import exceptions.IncorrectDaoOperation;
import interfaces.ICrudDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao extends Dao implements ICrudDao<User, Long> {

    private static final String SQL_AUTH = "select * from users where username=? and password=?";
    private static final String SQL_INSERT = "insert into users (username, password, email, first_name, last_name, profile) values(?,?,?,?,?,?)";
    private static final String SQL_EXIST_BY_USERNAME = "select 1 from usuarios where username=?";
    private static final String SQL_GET_BY_ID = "select * from users where user_id=?";
    private static final String SQL_ALL = "select user_id, username, email, first_name, last_name, profile from users";
    private static final String SQL_UPDATE = "update users set username=?, email=?, first_name=?, last_name=?, profile=? where user_id=?";

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
                        res.getString("profile"));
                usr.setFirstname(res.getString("first_name"));
                usr.setLastname(res.getString("last_name"));
                usr.setEmail(res.getString("email"));
            }

        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }

        return usr;
    }

    @Override
    public boolean insert(User usr) {
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
                ps.setString(5, usr.getProfile());
                ps.setLong(6, usr.getId());

                if (ps.executeUpdate() > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new IncorrectDaoOperation(e.getMessage());
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
                        res.getString("profile"));
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
                        res.getString("profile"));
                users.add(usr);
            }
        } catch (SQLException ex) {
            throw new IncorrectDaoOperation(ex.getMessage());
        }
        return users;
    }

    @Override
    public boolean delete(Long id) {
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

}
