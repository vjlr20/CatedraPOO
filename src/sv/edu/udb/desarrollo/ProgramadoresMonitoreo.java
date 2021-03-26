/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.desarrollo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.db.Conexion;

/**
 *
 * @author Victor López
 */
public class ProgramadoresMonitoreo extends javax.swing.JInternalFrame {
    DefaultTableModel modeloProgra = null, modeloCasos = null, modeloBitacora = null;
    public static int bandera = 0;
    ResultSet resultado = null;
    
    Conexion conex = new Conexion();
    
    ArrayList<ArrayList<String>> usuarios = null, casos = null;
    
    /**
     * Creates new form ProgramadoresMonitoreo
     */
    public ProgramadoresMonitoreo() throws SQLException {
        initComponents();
        
        Object[][] data = null;
        
        String[] columnas = { "#", "Programador", "Usuario",  "# de Casos" };
        
        modeloProgra = new DefaultTableModel(data, columnas);
        this.programadoresjTable.setModel(modeloProgra);
        
        conex.setRs("SELECT usuarios.usuario_id, CONCAT(usuarios.nombres, ' ', usuarios.apellidos) AS programador, usuarios.usuario, COUNT(*) AS numero_casos FROM caso INNER JOIN usuarios ON usuarios.usuario_id = caso.programador GROUP BY CONCAT(usuarios.nombres, ' ', usuarios.apellidos)");
        
        listarProgramadores();
    }
    
    private void listarProgramadores() throws SQLException {
        usuarios = new ArrayList<>();
        
        ResultSet rs = conex.getRs();
        
        int i = 0;
        
        while (rs.next()) {           
            Object[] newRow = {
                rs.getInt(1), 
                rs.getString(2), 
                rs.getString(3), 
                rs.getString(4),
            };
            
            ArrayList<String> usuario = new ArrayList<>();
            
            usuario.add(rs.getString(1));
            usuario.add(rs.getString(2));
            usuario.add(rs.getString(3));
            usuario.add(rs.getString(4));
            usuario.add(String.valueOf(i));
            
            usuarios.add(usuario);
            
            i++;
            
            modeloProgra.addRow(newRow);
        }
        
        rs.close();
        System.out.println(usuarios);
    }
    
    private void mostrarCasosProgramador(int id, String user) throws SQLException {
        casos = new ArrayList<>();
        
        Object[][] datos = null;
        
        String[] info = { "Código", "Departamento", "Caso",  "Fecha entrega", "Estado" };
        
        modeloCasos = new DefaultTableModel(datos, info);
        this.casosProgramadorjTable.setModel(modeloCasos);
        
        modeloCasos.setRowCount(0);
        
        String sql = "SELECT caso.caso_id, caso.codigo, departamento.departamento, solicitud.caso, caso.fecha_limite, estado_caso.estado FROM caso INNER JOIN solicitud ON caso.solicitud_id = solicitud.solicitud_id INNER JOIN departamento ON solicitud.departamento_id = departamento.departamento_id INNER JOIN estado_caso ON caso.estado = estado_caso.estado_caso_id WHERE caso.programador = " + id;
        
        conex.setRs(sql);
        
        ResultSet rs = conex.getRs();
        
        int i = 0;
        
        while (rs.next()) {           
            Object[] newRow = {
                rs.getString(2), 
                rs.getString(3), 
                rs.getString(4), 
                rs.getString(5), 
                rs.getString(6),
            };
            
            ArrayList<String> caso = new ArrayList<>();
            
            caso.add(rs.getString(1));
            caso.add(rs.getString(2));
            caso.add(rs.getString(3));
            caso.add(rs.getString(4));
            caso.add(rs.getString(5));
            caso.add(rs.getString(6));
            caso.add(String.valueOf(i));
            
            casos.add(caso);
            
            i++;
            
            modeloCasos.addRow(newRow);
        }
        
        rs.close();
        
        lblProgramador.setText(user);
    }
    
    private void mostrarBitacora(int id) throws SQLException {
        // casos = new ArrayList<>();
        
        Object[][] datos = null;
        
        String[] info = { "Bitacora", "Progreso", "Fecha de registro" };
        
        modeloBitacora = new DefaultTableModel(datos, info);
        this.bitacorajTable.setModel(modeloBitacora);
        
        modeloBitacora.setRowCount(0);
        
        String sql = "SELECT * FROM bitacora WHERE caso_id = " + id;
        
        conex.setRs(sql);
        
        ResultSet rs = conex.getRs();
        
        int i = 0;
        
        while (rs.next()) {           
            Object[] newRow = {
                rs.getString(3), 
                rs.getString(4), 
                rs.getString(5),
            };
            
            i++;
            
            modeloBitacora.addRow(newRow);
        }
        
        rs.close();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        programadoresjTable = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblProgramador = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        casosProgramadorjTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bitacorajTable = new javax.swing.JTable();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        programadoresjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        programadoresjTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        programadoresjTable.setName("programadoresjTable"); // NOI18N
        programadoresjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                programadoresjTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(programadoresjTable);

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        jLabel1.setText("Buscar:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Programador"));

        jLabel2.setText("Programador:");

        lblProgramador.setText("________________");

        jLabel4.setText("Casos:");

        casosProgramadorjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        casosProgramadorjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                casosProgramadorjTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(casosProgramadorjTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblProgramador)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProgramador)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bitacora del caso"));

        bitacorajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(bitacorajTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void programadoresjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_programadoresjTableMouseClicked
        int fila = programadoresjTable.rowAtPoint(evt.getPoint());
        // System.out.println(fila);
        for (int i = 0; i < usuarios.size(); i++) {
            if (fila == Integer.parseInt(usuarios.get(i).get(4))) {
                try {
                   mostrarCasosProgramador(Integer.parseInt(usuarios.get(i).get(0)), usuarios.get(i).get(1));
                } catch (SQLException ex) {
                    Logger.getLogger(ProgramadoresMonitoreo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }//GEN-LAST:event_programadoresjTableMouseClicked

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        /*while (modeloCasos.getRowCount() != 0) modeloCasos.removeRow(0) ;

        String busqueda = txtBuscar.getText();

        String query1 = query + " AND solicitud.caso LIKE '%" + busqueda + "%' ";

        conex.setRs(query1);

        try {
            listarCasos();
        } catch (SQLException ex) {
            System.out.println(ex);
            // Logger.getLogger(AlumnosListado.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        try {
            bandera = 0;
            
            this.dispose();
            
            conex.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex);
            // Logger.getLogger(AlumnosListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void casosProgramadorjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_casosProgramadorjTableMouseClicked
        int fila = programadoresjTable.rowAtPoint(evt.getPoint());
        
        for (int i = 0; i < casos.size(); i++) {
            if (fila == Integer.parseInt(casos.get(i).get(6))) {
                try {
                   mostrarBitacora(Integer.parseInt(casos.get(i).get(0)));
                } catch (SQLException ex) {
                    Logger.getLogger(ProgramadoresMonitoreo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }//GEN-LAST:event_casosProgramadorjTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bitacorajTable;
    private javax.swing.JTable casosProgramadorjTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblProgramador;
    private javax.swing.JTable programadoresjTable;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
