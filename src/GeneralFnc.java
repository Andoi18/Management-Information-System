/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;

/**
 *
 * @author Drew
 */
public class GeneralFnc {
    
    public int generateRandomID(int length, String startingNum) {
        if (length <= 1) {
            throw new IllegalArgumentException("Length must be greater than 1");
        }

        StringBuilder sb = new StringBuilder(startingNum);
        Random random = new Random();

        for (int i = startingNum.length(); i < length; i++) {
            int digit = random.nextInt(10); // Generate a random integer between 0 and 9
            sb.append(digit);
        }

        return Integer.parseInt(sb.toString());
    }
    
}
