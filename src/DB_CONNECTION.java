import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_CONNECTION {
    Connection connection;
    Statement statement;
    DB_CONNECTION(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql:///bank_db", "root", "Durgapur@1");
            statement = connection.createStatement();
            System.out.println("Database connected successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
