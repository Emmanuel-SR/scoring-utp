package connection;

import java.sql.Connection;
import util.Assert;

public class ConnectionTest {

    public static void main(String[] args) {
        Connection cnn = ConnectionDB.getInstance().getConnection();
        Assert.notNull(cnn, "Test connection failed");
    }
}
