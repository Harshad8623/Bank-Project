package pk;

import java.sql.*;

public class DatabaseConnection 
{

    
    static Connection GETConnection() 
    {
        try 
        {
            String url = "jdbc:mysql://localhost:3306/bankdb2";
            String user = "root";
            String pass = "root";

            return DriverManager.getConnection(url, user, pass);

        } 
        catch (Exception e)
        {
            System.out.println("Database Connection Problem : " + e);
            return null ;
        }
    }
}
