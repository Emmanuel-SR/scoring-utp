package daos;

import connection.ConnectionDB;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao<T, ID extends Serializable> {

    protected static PreparedStatement ps = null;

    protected static ResultSet res = null;

    protected final ConnectionDB cnn = ConnectionDB.getInstance();

    public Dao() {
    }

    protected void closeConnection() {
        try {
            if (res != null) {
                res.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.closeConnection();
            }
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
    }

}
