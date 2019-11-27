package daos;

import dtos.User;
import exceptions.IncorrectDaoOperation;
import java.sql.SQLException;

public class userDao extends Dao<User, Long> {
    
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
    
}
