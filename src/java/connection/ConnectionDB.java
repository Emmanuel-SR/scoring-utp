package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    private static ConnectionDB instance = null;

    private static Connection cnn = null;

    private static final String URL = "jdbc:mysql://localhost:3306/scoring_utp_db";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "root1234";

    private ConnectionDB() {
        try {

            Class.forName(DRIVER).newInstance();

            cnn = DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized static ConnectionDB getInstance() {

        if (instance == null) {
            instance = new ConnectionDB();
        }

        return instance;

    }

    public Connection getConnection() {
        return cnn;
    }

    public void closeConnection() {
        instance = null;
    }

}
