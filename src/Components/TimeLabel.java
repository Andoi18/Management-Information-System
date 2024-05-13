package Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Drew
 */

public class TimeLabel extends JLabel  {
    public TimeLabel() {
        setFont(new Font("Arial", Font.BOLD, 16));
        updateTimeLabel();

        // Update label every second
        Timer timer = new Timer(1000, e -> updateTimeLabel());
        timer.start();
    }

    private void updateTimeLabel() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedDateTime = now.format(formatter);
        setText("Current Time: \n" + formattedDateTime);
    }
}
