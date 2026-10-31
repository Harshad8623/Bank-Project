package pk;

import java.sql.*;

public class DatabaseConnection 
{

    
    static Connection GETConnection() 
    {
        try 
        {
            String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/bankdb2");
            String user = System.getenv().getOrDefault("DB_USER", "root");
            String pass = System.getenv("DB_PASSWORD");

            if (pass == null || pass.isBlank()) {
                throw new IllegalStateException("Set DB_PASSWORD in the environment.");
            }
            return DriverManager.getConnection(url, user, pass);

        } 
        catch (Exception e)
        {
            System.out.println("Database Connection Problem : " + e);
            return null ;
        }
    }
}
