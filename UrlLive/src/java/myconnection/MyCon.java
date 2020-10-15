package myconnection;
import java.sql.*;
public class MyCon 
{
    static Connection con = null;
    static
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Driver Load");
        }
        catch (ClassNotFoundException e) 
        {
            System.out.println(e);
        }
    }
    public static Connection getConnection()
    {
        try 
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/url","root","root");
            //System.out.println("Connection Stablished");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return con;
    }
}