/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pinapp;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author ilya
 */
public class PinApp {

    private static final int[] pins = new int[20000];  
   
    static {  
        for (int i = 0; i < pins.length; i++)  
            pins[i] = 100000 + i;  
    } 
    
    private static int pinCount;  
   
    private static final Random random = new Random();  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
        PrintWriter out = new PrintWriter("/home/ilya/keys.txt");
        
        for (int i = 0; i < pins.length; i++)  
        {
            out.println(MD5(""+generatePin()).substring(1,8));
        }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Генерация успешно завершена");
    }
    
public static String MD5(String md5) {
   try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(md5.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
}

    public static int generatePin() {  
        if (pinCount >= pins.length)  
            throw new IllegalStateException();  
        int index = random.nextInt(pins.length - pinCount) + pinCount;  
        int pin = pins[index];  
        pins[index] = pins[pinCount++];  
        return pin;  
    }   
}

