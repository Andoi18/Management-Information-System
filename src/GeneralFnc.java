/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Drew
 */
public class GeneralFnc {
    
    public String generateRandomID(int length, String startingNum) {
        if (length <= 1) {
            throw new IllegalArgumentException("Length must be greater than 1");
        }
        StringBuilder sb = new StringBuilder(startingNum);
        Random random = new Random();

        for (int i = startingNum.length(); i < length; i++) {
            int digit = random.nextInt(10); // Generate a random integer between 0 and 9
            sb.append(digit);
        }

        return (sb.toString());
    }
    
    public boolean isEmail(String email) {
        // Regular expression pattern for validating email addresses
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        
        // Creating a Pattern object
        Pattern regex = Pattern.compile(pattern);
        
        // Creating a Matcher object
        Matcher matcher = regex.matcher(email);
        
        // Using Matcher.matches() to check if the email matches the pattern
        return matcher.matches();
    }
    
    public String getCurrentDateTime(){
        Date d = new Date();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
    public String dateFormatter(Date date){
        if(date == null)return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    
    public void accountSetup(int userID, Map<String, String> data){
        db_connection db = new db_connection();
        if(!db.existsInDb("userdata", "userID", Integer.toString(userID))){
            db.addToDb(userID, "userdata", "userID", Integer.toString(userID));
        }
        data.forEach((column, value) -> {
            System.out.println(column + " " + value);
            db.updateDb(userID, "userdata", column, value);
        });
        
        db.updateDb(userID, "users", "accSetup", "0");
        db.dbLog(userID, "Employee", "Account", "Finnished Account Setup");
        return;       
    }
    
    public String[] getGenders(){
        String[] genders = {"Male", "Female", "Non-Binary", "Prefer not to say"};
        return genders;
    }
    
    //FOR TEXTFIELDS
    public void alphaNumericFilter(java.awt.event.KeyEvent evt, int filter){
        char c = evt.getKeyChar();
        switch(filter){
            case 0:
                evt.consume(); // Ignore the event
                return;
            case 1:
                if (!Character.isDigit(c)) {
                    evt.consume(); // Ignore the event
                }
                return;
            case 2:
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    evt.consume(); // Ignore the event
                }
                return;
            default:
                if (!Character.isLetterOrDigit(c)) {
                    evt.consume(); // Ignore the event
                }
                return;
        }
    }
    
    //CAPITALIZES EVERY FIRST LETTER OF A WORD FROM A STRING
    public String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Return input if it's null or empty
        }
        
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true; // Flag to indicate if the next character should be capitalized
        
        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true; // Set flag to true to capitalize the next character
                result.append(c); // Add whitespace to the result
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c)); // Capitalize the character
                capitalizeNext = false; // Reset the flag
            } else {
                result.append(c); // Add the character as it is
            }
        }
        return result.toString();
    }
    
    public void filterMyLogsRows(JTable table, Object valueToSearch, String searchFrom) {

        // Create a RowFilter to filter rows based on the search criteria
        RowFilter<Object, Object> rowFilter = new RowFilter<Object, Object>() {
            @Override
            public boolean include(Entry<? extends Object, ? extends Object> entry) {
                // Get the value from the specified column
                String value = entry.getValue(table.getColumnModel().getColumnIndex(searchFrom)).toString();
                // Check if the value matches the search criteria
                return value != null && value.toLowerCase().contains(valueToSearch.toString().toLowerCase());
            }
        };

        // Apply the RowFilter to the TableRowSorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setRowFilter(rowFilter);
        table.setRowSorter(sorter);
    }
    
    public boolean getConfirmation(String description, String title){
        int result = JOptionPane.showConfirmDialog(null ,description, title,
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
           return true;
        }else if (result == JOptionPane.NO_OPTION){
           return false;
        }else {
           return false;
        }
    }
    
    public void passwordFieldBehavior(JPasswordField pf, JCheckBox cb){
        if(cb.isSelected()){
            pf.setEchoChar((char)0);
        }
        else{
            pf.setEchoChar((char)'*');
        }
    }
    
    /*
    BUTTON PLACEHOLDERS
    */
    
    public void addPlaceHolderStyle(JTextField tf){
        Font font = tf.getFont();
        font = font.deriveFont(Font.ITALIC);
        tf.setFont(font);
        tf.setForeground(Color.DARK_GRAY);
    
    }
    
     public void removePlaceHolderStyle(JTextField tf){
        Font font = tf.getFont();
        font = font.deriveFont(Font.PLAIN | Font.BOLD);
        tf.setFont(font);
        tf.setForeground(Color.BLACK);
    
    }
     
    public void tfFocusGain(JTextField tf, String text){
        if(tf.getText().equals(text)){
            tf.setText(null);
            tf.requestFocus();
            removePlaceHolderStyle(tf);
        }
    }
    public void pfFocusGain(JPasswordField pf, String text){
        if(pf.getText().equals("Password")){
            pf.setText(null);
            pf.requestFocus();
            pf.setEchoChar('*');
            removePlaceHolderStyle(pf);
        }
    }
    public void tfFocusLost(JTextField tf, String text){
         if(tf.getText().isBlank() || tf.getText().isEmpty()){
            addPlaceHolderStyle(tf);
            tf.setText(text);
        }
    }
    public void pfFocusLost(JPasswordField pf, String text){
        if(pf.getText().isBlank() || pf.getText().isEmpty()){
           addPlaceHolderStyle(pf);
           pf.setText(text);
           pf.setEchoChar('\u0000');
        }
    }
}
