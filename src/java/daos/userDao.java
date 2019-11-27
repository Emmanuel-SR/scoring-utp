package daos;

import static daos.Dao.ps;
import static daos.Dao.res;
import dtos.User;
import exceptions.IncorrectDaoOperation;
import interfaces.ICrudDao;
import java.sql.SQLException;
import java.util.List;

public class userDao extends Dao implements ICrudDao<User, Long> {

    private static final String SQL_AUTH = "select * from users where username=? and password=?";

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
        return false;
    }

    @Override
    public User get(Long primaryKey) {
        return null;
    }

    @Override
    public List<User> all() {
        return null;
    }

    @Override
    public boolean delete(User resourse) {
        return false;
    }

}
