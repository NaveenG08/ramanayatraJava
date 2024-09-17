package DatabaseProcessor.OperationRequest;

import java.sql.*;

public class DBAction
{
    private static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        String url = "jdbc:mysql://localhost:3306/ramanayatra";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
    public static ResultSet getDataFromDB(String query) throws SQLException, ClassNotFoundException
    {
        Connection connection = DBAction.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result =  statement.executeQuery(query.toString());
        return result;
    }
    public static boolean putDataInDB(String query) throws SQLException, ClassNotFoundException
    {
        Connection connection = DBAction.getConnection();
        Statement statement = connection.createStatement();
        boolean result = statement.execute(query.toString());
        connection.close();
        return result;
    }
    public static void deleteDatainDB(String query) throws SQLException, ClassNotFoundException
    {
        Connection connection = DBAction.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(query.toString());
        connection.close();
    }
}
