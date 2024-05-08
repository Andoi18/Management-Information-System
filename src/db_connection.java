/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Drew
 */
public class db_connection {
    GeneralFnc fnc = new GeneralFnc();
    
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
        Date d = new Date();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String tempID;
                
            do{
                tempID = Integer.toString(fnc.generateRandomID(10, Integer.toString(d.getYear() + 1900)));
            }
            while(existsInDb("users", "userID", tempID));
            
            
            String sql = "INSERT INTO `users`"
                    + "(`userID`, `username`, `password`, `admin`, `archived`, `active`, `datetimeCreated`) VALUES "
                    + "('"+ tempID +"', '"+ username +"','"+ pass +"','0','0','0','"+ formattedDateTime +"')";
            try (Statement statement = connection.createStatement()){
                int rowsInserted = statement.executeUpdate(sql);
                if (rowsInserted > 0) {
                    System.out.println("A new row has been inserted successfully.");
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }    }
    
    
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
    
    public boolean existsInDb(String table, String column, String input){
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "SELECT * FROM "+ table +" WHERE "+ column +"= "+ input +"";
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
