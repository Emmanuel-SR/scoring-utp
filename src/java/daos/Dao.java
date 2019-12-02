package daos;

import connection.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao {

    protected PreparedStatement ps = null;

    protected ResultSet res = null;

    protected final ConnectionDB cnn = ConnectionDB.getInstance();

    public static final int MYSQL_DUPLICATE_PK = 1062;

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
