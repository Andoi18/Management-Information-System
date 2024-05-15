/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
 */
import java.sql.*;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

        }
    }
    
    public String register(String username, String pass){
        Date d = new Date();
        String formattedDateTime = fnc.getCurrentDateTime();
        
        try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String tempID;
                
            do{
                tempID = fnc.generateRandomID(10, Integer.toString(d.getYear() + 1900));
            }
            while(existsInDb("users", "userID", tempID));
            
            
            String sql = "INSERT INTO `users`"
                    + "(`userID`, `username`, `password`, `admin`, `archived`, `active`, `datetimeCreated`) VALUES "
                    + "('"+ tempID +"', '"+ username +"','"+ pass +"','1','1','1','"+ formattedDateTime +"')";
            try (Statement statement = connection.createStatement()){
                int rowsInserted = statement.executeUpdate(sql);
                if (rowsInserted > 0) {
                    System.out.println("A new row has been inserted successfully.");
                    return tempID;
                }
                return "0";
            }
        } catch (SQLException e) {
            return "0";
        }    
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
    
    public boolean existsInDb(String table, String column, String input){
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "SELECT * FROM "+ table +" WHERE "+ column +"= '"+ input +"'";
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
    
    public Object getUserData(int userID, String table, String column){
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
    
    public void addToDb(int userID, String table, String column, String value){
        String formattedDateTime = fnc.getCurrentDateTime();
        
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "INSERT INTO `"+table+"`("+column+") VALUES ('"+value+"')";
            try (Statement statement = connection.createStatement()){
                int rowsInserted = statement.executeUpdate(sql);
                if (rowsInserted > 0) {
                    System.out.println("A new row has been inserted successfully.");
                    return;
                }
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }    
    }
    
    public void updateDb(int userID, String table, String column, String value){
        String formattedDateTime = fnc.getCurrentDateTime();
        
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String sql = "UPDATE "+table+" SET "+column+" = '"+value+"' WHERE userID="+userID;;
            Statement statement = connection.prepareStatement(sql);
            int rowsUpdated = statement.executeUpdate(sql);
            if (rowsUpdated > 0) {
                System.out.println("Row/s has been upadted successfully.");
                return;
            }
        } catch (SQLException e) {
        }    
    }
    
    public void dbLog(int userID, String by, String type, String message){
        String formattedDateTime = fnc.getCurrentDateTime();
         try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            String tempID;
            do{
                tempID = fnc.generateRandomID(15, "1");
            }
            while(existsInDb("logs", "logID", tempID));
             
            String sql = "INSERT INTO `logs`(`logID`, `loggerID`, `loggedBy`, `type`, `message`, `dateTime`) "
                        + "VALUES ('"+tempID+"', '"+ userID+"','"+by+"','"+type+"','"+message+"','"+ formattedDateTime+"')";
            try (Statement statement = connection.createStatement()){
                int rowsInserted = statement.executeUpdate(sql);
                if (rowsInserted > 0) {
                    System.out.println("A new row has been inserted successfully.");
                    return;
                }
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }    
    }
    
    public DefaultTableModel getLogsTableModel(String column, String input, boolean isAdmin){
        DefaultTableModel model = new DefaultTableModel();
        String query;
        try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            if(!isAdmin){
                query = "SELECT * FROM `logs` WHERE " + column + " = '" + input +"'";
            }
            else{
                query = "SELECT `logID`,`type`, `message`, `dateTime` FROM `logs`";
            }
            
            
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)){
                // Get metadata
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Create column names vector
                Vector<String> columnNames = new Vector<>();
                for (int col = 1; col <= columnCount; col++) {
                    columnNames.add(metaData.getColumnName(col));                   
                }

                // Create data vector
                Vector<Vector<Object>> data = new Vector<>();
                while (rs.next()) {
                    Vector<Object> vector = new Vector<>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        vector.add(rs.getObject(columnIndex));
                    }
                    data.add(vector);
                }

                // Create table model
                model = new DefaultTableModel(data, columnNames);
                return model;
                }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public DefaultTableModel getStatusModel(){
        DefaultTableModel model = new DefaultTableModel();
        String query;
        try (Connection connection = DriverManager.getConnection(url, user, pw)) {
            query = "SELECT `userID`, `username` , `archived`, `active`, `datetimeCreated`, `accSetup`, `availability`, `statusMessage` FROM `users` WHERE 1";
            
            
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)){
                // Get metadata
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Create column names vector
                Vector<String> columnNames = new Vector<>();
                for (int col = 1; col <= columnCount; col++) {
                    columnNames.add(metaData.getColumnName(col));                   
                }

                // Create data vector
                Vector<Vector<Object>> data = new Vector<>();
                while (rs.next()) {
                    Vector<Object> vector = new Vector<>();
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        vector.add(rs.getObject(columnIndex));
                    }
                    data.add(vector);
                }

                // Create table model
                model = new DefaultTableModel(data, columnNames);
                return model;
                }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}
