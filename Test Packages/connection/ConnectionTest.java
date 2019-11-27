package connection;

import util.Assert;

public class ConnectionTest {

    public static void main(String[] args) {
        ConnectionDB cnn = ConnectionDB.getInstance();
        Assert.notNull(cnn, "Test connection failed");
    }
}
