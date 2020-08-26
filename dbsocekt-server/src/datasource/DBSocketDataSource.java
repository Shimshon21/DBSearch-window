package datasource;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBSocketDataSource {
    //To make sure the mysql dependency is exist.
    static {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //Connect to localhost data base named businessDB.
    public Connection getConnection() throws Exception {
        Connection con =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/buisnessDB", "admin", "admin");
        return con;
    }
}
