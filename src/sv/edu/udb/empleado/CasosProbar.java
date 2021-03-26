/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.empleado;

/**
 *
 * @author Victor López
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.db.Conexion;

public class CasosProbar extends javax.swing.JInternalFrame {
    DefaultTableModel modeloCasos = null, modeloBitacora = null;
    
    static int empleado = 0;
    public static int bandera = 0;
    
    ArrayList<ArrayList<String>> casos = null;
    
    Conexion conex = new Conexion();
    
    /**
     * Creates new form CasosProbar
     */
    public CasosProbar(int idEmpleado) throws SQLException {
        initComponents();
        
        empleado = idEmpleado;
        
        bandera = 1;
        
        Object[][] data = null;
        
        String[] columnas = { "#", "Caso", "Descripción", "Fecha de entrega" };
        
        modeloCasos = new DefaultTableModel(data, columnas);
        this.casosJTable.setModel(modeloCasos);
        
        listarCasosEspera(empleado);
    }
    
    private void listarCasosEspera(int id) throws SQLException {
        modeloCasos.setRowCount(0);
        
        casos = new ArrayList<>();
        
        String sql = "SELECT caso.codigo, solicitud.caso, solicitud.descripcion, caso.fecha_limite, caso.caso_id, caso.solicitud_id FROM caso INNER JOIN solicitud ON caso.solicitud_id = solicitud.solicitud_id INNER JOIN usuarios ON caso.programador = usuarios.usuario_id WHERE caso.tester = " + id + " AND caso.estado = 2";
        
        conex.setRs(sql);
        
        ResultSet rs = conex.getRs();
        
        int i = 0;
        
        while (rs.next()) {
            i++;
            
            Object[] newRow = {
                i,
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
            };
            
            ArrayList<String> caso = new ArrayList<>();
            
            caso.add(rs.getString(1));
            caso.add(rs.getString(2));
            caso.add(rs.getString(3));
            caso.add(rs.getString(4));
            caso.add(rs.getString(5));
            caso.add(String.valueOf(i));
            caso.add(rs.getString(6));
            
            casos.add(caso);
            
            modeloCasos.addRow(newRow);
        }
        
        rs.close();
    }
    
    private void mostrarCaso(ArrayList<String> info) throws SQLException {
        // System.out.println(info);
        lblID.setText(info.get(4));
        lblCodigo.setText(info.get(0));
        lblCaso.setText(info.get(1));
        txtDescripcion.setText(info.get(2));
        lblEntrega.setText(info.get(3));
    }
    
    private void limpiarDatos() {
        lblID.setText("___________");
        lblCodigo.setText("___________");
        lblCaso.setText("________________________________________________");
        txtDescripcion.setText("");
        lblEntrega.setText("___________");
        txtObervaciones.setText("");
        
        txtObervaciones.setEnabled(false);
        
        AccionesGroup.clearSelection();
    }
    
    private void completarSolicitud(int id) throws SQLException {
        String query = "UPDATE solicitud SET estado = 4 WHERE solicitud_id = " + id;
        
        conex.setQuery(query);
    }
    
    private void aprobarCaso(int id) throws SQLException {
        String query = "UPDATE caso SET estado = 3 WHERE caso_id = " + id;
        
        conex.setQuery(query);
        
        JOptionPane.showMessageDialog(this, "Caso Aprobado");
        
        limpiarDatos();
        listarCasosEspera(empleado);
    }
    
    private void rechazarCaso(int id) throws SQLException {
        String query = "UPDATE caso SET estado = 1 WHERE caso_id = " + id;
        
        conex.setQuery(query);
        
        JOptionPane.showMessageDialog(this, "Caso Rechazado");
        
        limpiarDatos();
        listarCasosEspera(empleado);
    }
    
    private void agregarObservacion(String contenido, int id) throws SQLException {
        String query = "INSERT INTO observaciones (caso, observacion) VALUES (" + id + ", '" + contenido + "')";
        
        conex.setQuery(query);
    }
    
    private void mostrarBitacora(String id) throws SQLException {
        Object[][] datos= null;
        
        String[] columns = { "#", "Caso", "Descripción", "Fecha de entrega" };
        
        modeloBitacora = new DefaultTableModel(datos, columns);
        this.bitacoraJTable.setModel(modeloBitacora);
        
        modeloBitacora.setRowCount(0);
        
        String sql = "SELECT bitacora.descripcion, bitacora.progreso, bitacora.fecha_registro FROM bitacora WHERE bitacora.caso_id = " + id;
        
        conex.setRs(sql);
        
        ResultSet rs = conex.getRs();
        
        int i = 0;
        
        while (rs.next()) {
            i++;
            
            Object[] newRow = {
                i,
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
            };
            
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

        AccionesGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        casosJTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bitacoraJTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblCaso = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lblEntrega = new javax.swing.JLabel();
        rdbAprobar = new javax.swing.JRadioButton();
        rdbRechazar = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObervaciones = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();

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

        casosJTable.setModel(new javax.swing.table.DefaultTableModel(
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
        casosJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                casosJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(casosJTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bitacora"));

        bitacoraJTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(bitacoraJTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles del caso"));

        jLabel1.setText("Código:");

        lblCodigo.setText("___________");

        lblCaso.setText("________________________________________________");

        jLabel4.setText("Caso:");

        jLabel2.setText("Descripción:");

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setToolTipText("");
        jScrollPane3.setViewportView(txtDescripcion);

        jLabel3.setText("Fecha de entrega:");

        lblEntrega.setText("___________");

        AccionesGroup.add(rdbAprobar);
        rdbAprobar.setText("Aprobar");
        rdbAprobar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbAprobarStateChanged(evt);
            }
        });

        AccionesGroup.add(rdbRechazar);
        rdbRechazar.setText("Rechazar");
        rdbRechazar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbRechazarStateChanged(evt);
            }
        });

        txtObervaciones.setColumns(20);
        txtObervaciones.setRows(5);
        txtObervaciones.setEnabled(false);
        jScrollPane4.setViewportView(txtObervaciones);

        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel5.setText("ID:");

        lblID.setText("___________");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCaso, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lblEntrega)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdbAprobar)
                        .addGap(76, 76, 76)
                        .addComponent(rdbRechazar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4))
                .addGap(18, 18, 18)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblID)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCodigo)
                .addGap(107, 107, 107))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblID))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblCodigo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblCaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblEntrega))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbAprobar)
                    .addComponent(rdbRechazar))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void casosJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_casosJTableMouseClicked
        btnAceptar.setEnabled(true);
        
        int fila = casosJTable.rowAtPoint(evt.getPoint());
        
        for (int i = 0; i < casos.size(); i++) {
            if (fila == (Integer.parseInt(casos.get(i).get(5)) - 1)) {
                try {
                    mostrarCaso(casos.get(i));
                    mostrarBitacora(casos.get(i).get(4));
                } catch (SQLException ex) {
                    Logger.getLogger(CasosProbar.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }//GEN-LAST:event_casosJTableMouseClicked

    private void rdbAprobarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbAprobarStateChanged
        if (rdbAprobar.isSelected()) {
            txtObervaciones.setEnabled(false);
        }
    }//GEN-LAST:event_rdbAprobarStateChanged

    private void rdbRechazarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbRechazarStateChanged
        if (rdbRechazar.isSelected()) {
            txtObervaciones.setEnabled(true);
        }
    }//GEN-LAST:event_rdbRechazarStateChanged

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        int id = Integer.parseInt(lblID.getText());
        
        int error = 0;
        
        if (rdbAprobar.isSelected()) {
            int solicitud = 0;
            
            for (int i = 0; i < casos.size(); i++) {
                if (id == Integer.parseInt(casos.get(i).get(4))) {
                    solicitud = Integer.parseInt(casos.get(i).get(6));
                    break;
                }
                
                /*int j = 0;
                
                for (String item : casos.get(i)) {
                    System.out.println(j + " ->" + item);
                    j++;
                }*/
            }
            
            try {
                completarSolicitud(solicitud);
                aprobarCaso(id);
            } catch (SQLException ex) {
                Logger.getLogger(CasosProbar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (rdbRechazar.isSelected()) {
            String observaciones = txtObervaciones.getText();
            
            if (observaciones.replace(" ", "").length() < 1) {
                JOptionPane.showMessageDialog(null, "Ingrese las observaciones sobre el caso a rechazar", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (error < 1) {
                try {
                    agregarObservacion(observaciones, id);
                    rechazarCaso(id);
                } catch (SQLException ex) {
                    Logger.getLogger(CasosProbar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AccionesGroup;
    private javax.swing.JTable bitacoraJTable;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JTable casosJTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCaso;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEntrega;
    private javax.swing.JLabel lblID;
    private javax.swing.JRadioButton rdbAprobar;
    private javax.swing.JRadioButton rdbRechazar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtObervaciones;
    // End of variables declaration//GEN-END:variables
}
