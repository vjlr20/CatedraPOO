/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

/**
 *
 * @author Victor López
 */

// import java.io.IOException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

public class ErrorLog {
    private FileHandler fh = null;
    
    public void registrarLog(Logger logger, String log, String clase)  {
        try {
            SimpleFormatter formatter = new SimpleFormatter();
            SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
            
            String path = "log/log_" + format.format(Calendar.getInstance().getTime()) + ".log";
            System.out.println(log + "\n");
            System.out.println(clase + "\n");
            System.out.println(clase + "\n");          
            fh = new FileHandler(path);
            logger.addHandler(fh);

            
            fh.setFormatter(formatter);

            logger.info(log);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
