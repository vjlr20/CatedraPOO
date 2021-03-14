/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.db;

/**
 *
 * @author Victor López
 */

import java.sql.*;

public class Conexion {
    private Connection conexion = null;
    private Statement s = null;
    private ResultSet rs = null;
    private String query = "";

    public Conexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection("jdbc:mysql://localhost/catedra", "root", "");
            
            s = conexion.createStatement();
        } catch (ClassNotFoundException e1) {
            System.out.println("ERROR:No encuentro el driver de la BD: " + e1.getMessage());
        }
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(String consulta) {
        try {
            this.rs = s.executeQuery(consulta);
        } catch (SQLException e2) {
            System.out.println("ERROR: Fallo en SQL: " + e2.getMessage());
        }
    }

    public void setQuery(String query) throws SQLException {
        this.s.executeUpdate(query);
    }

    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
}
