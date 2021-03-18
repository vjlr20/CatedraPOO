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

import java.util.regex.*;

public class Validaciones {
    // Validaciones necesarias para el ingreso de datos
    public boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher mather = pattern.matcher(email);
        
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
}
