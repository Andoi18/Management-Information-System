/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Drew
 */
public class db_connection {
    protected Connection conn = null;
    protected String url = "jdbc:mysql://localhost:3306/management_information_system?zeroDateTimeBehavior=CONVERT_TO_NULL";
    protected String user = "root";
    protected String pw = "";
    
    public void testConnection() {
        
        try {
            conn = DriverManager.getConnection(url, user, pw);
            if (conn != null) {
                JOptionPane.showMessageDialog(null, "Connected to the database", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occured", "Error", JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();

        }
    }
    
    public boolean register(String username, String pass){
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "INSERT INTO `users`"
                    + "(`userID`, `username`, `password`, `admin`, `archived`, `active`, `datetimeCreated`) VALUES "
                    + "('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]','[value-6]','[value-7]')";
            try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("userID");
                    String name = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    //6System.out.println(name + " " + password + " "+ username + " " + pass);
                    if(name.equals(username) && password.equals(pass)){
                        return true;
                    }
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    
    public int login(String username, String pass){
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "SELECT * FROM `users` WHERE username=\""+username+"\" AND password=\"" + pass +"\"";
            try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("userID");
                    String name = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    //6System.out.println(name + " " + password + " "+ username + " " + pass);
                    if(name.equals(username) && password.equals(pass)){
                        return id;
                    }
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }
    
    public boolean existsInDb(String table, String column, Object input){
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "SELECT * FROM '"+ table +"' WHERE '"+ column +"'= \""+ input +"\"";
            try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
                if (!resultSet.isBeforeFirst() ) {    
                    return false;
                } 
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    
    
    public Object getUserData(int userID, String table, String column, Object output){
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "SELECT "+ column +" FROM `"+ table+"` WHERE userID=\""+ userID +"\"";
            try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String result = resultSet.getString(column);
                    return result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }
    
}
