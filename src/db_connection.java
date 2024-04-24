/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Drew
 */
public class db_connection {
    
    void testConnection() {
         Connection conn = null;
       
        try {
            // connect way #1
            String url = "jdbc:mysql://localhost:3306/management_information_system?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "root";
            String password = "";
            
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                JOptionPane.showMessageDialog(null, "Connected to the database", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occured", "Error", JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();

        }


    }
}
