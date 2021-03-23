/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.admin;

/**
 *
 * @author Victor López
 */

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.db.Conexion;

public class DepartamentoMantenimiento extends javax.swing.JInternalFrame {
    DefaultTableModel modeloDepa = null;
    public static int bandera = 0;
    ResultSet resultado = null;
    
    ArrayList<ArrayList<String>> departamentos = null;
    ArrayList<ArrayList<String>> jefes = null;
    
    Conexion conex = new Conexion();
    
    /**
     * Creates new form DepartamentoMantenimiento
     */
    public DepartamentoMantenimiento() throws SQLException {
        initComponents();
        
        bandera = 1;
        
        initComponents();
        
        Object[][] data = null;
        
        String[] columnas = { "#", "Departamento", "Jefe", "Fecha de registro" };
        
        modeloDepa = new DefaultTableModel(data, columnas);
        this.DepartamentosjTable.setModel(modeloDepa);
        
        llenarComboJefes();
        
        listarDepartamentos();
    }
    
    private void llenarComboJefes() throws SQLException {
        jefes = new ArrayList<>();
        
        conex.setRs("SELECT usuarios.usuario_id, usuarios.nombres, usuarios.apellidos FROM departamento RIGHT JOIN usuarios ON usuarios.usuario_id = departamento.jefe WHERE usuarios.tipo_usuario = 2 AND usuarios.estado = 1");
        
        ResultSet rs = conex.getRs();
        
        cmbJefes.removeAllItems();
        
        int i = 0;
        
        while (rs.next()) {
            String persona = rs.getString(2) + " " + rs.getString(3);
            
            cmbJefes.addItem(persona);
            
            ArrayList<String> jefe = new ArrayList<>();
            
            jefe.add(rs.getString(1));
            jefe.add(rs.getString(2));
            jefe.add(rs.getString(3));
            jefe.add(String.valueOf(i));
            
            jefes.add(jefe);
            
            i++;
        }
    }
    
    private void listarDepartamentos() throws SQLException {
        departamentos = new ArrayList<>();
        
        modeloDepa.setRowCount(0);
        
        conex.setRs("SELECT departamento.departamento, usuarios.nombres, usuarios.apellidos, departamento.fecha_registro, departamento.departamento_id, usuarios.usuario_id FROM departamento INNER JOIN usuarios ON usuarios.usuario_id = departamento.jefe");
        
        resultado = conex.getRs();
        
        int i = 0;

        while (resultado.next()) {
            i++;
            
            Object[] newRow = {
                i, resultado.getString(1), resultado.getString(2) + " " + resultado.getString(3), resultado.getString(4)
            };
            
            ArrayList<String> depa = new ArrayList<>();
            
            depa.add(resultado.getString(5));
            depa.add(resultado.getString(1));
            depa.add(resultado.getString(2));
            depa.add(resultado.getString(3));
            depa.add(resultado.getString(6));
            depa.add(String.valueOf(i));
            
            departamentos.add(depa);

            modeloDepa.addRow(newRow);
        }

        resultado.close();
    }
    
    private void limpiarFormulario() {
        txtID.setText("");
        txtDepartamento.setText("");
        cmbJefes.setSelectedIndex(0);
        
        btnAccion.setText("Ingresar");
        btnAccion.setEnabled(false);
        
        btnEliminar.setEnabled(false);
    }
    
    private void mostrarDatos(ArrayList<String> info) {
        txtID.setText(info.get(0));
        txtDepartamento.setText(info.get(1));
        
        for (int i = 0; i < jefes.size(); i++) {
            if (info.get(4).equals(jefes.get(i).get(0))) {
                cmbJefes.setSelectedIndex(Integer.parseInt(jefes.get(i).get(3)));
                break;
            }
        }
        
        btnAccion.setText("Actualizar");
        btnAccion.setEnabled(true);
        
        btnEliminar.setEnabled(true);
    }
    
    private void borrarDepartamento(int id) {
        String sql = "DELETE FROM departamento WHERE departamento_id = " + id;
        
        try {
            conex.setQuery(sql);
            
            JOptionPane.showMessageDialog(this, "Departamento Eliminado Exitosamente");

            limpiarFormulario();
            
            listarDepartamentos();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void ingresoDatos(String depa, int jefeArea) {
        String sql = "INSERT INTO departamento (departamento, jefe) VALUES ("
                + "'" + depa + "', "
                + jefeArea + ")";
        
        try {
            conex.setQuery(sql);
            
            JOptionPane.showMessageDialog(this, "Departamento Ingresado Exitosamente");

            limpiarFormulario();
            
            listarDepartamentos();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void actualizarDatos(String depa, int jefeArea, int id) {
        String sql = "UPDATE departamento SET "
                + "departamento = '" + depa + "', "
                + "jefe = " + jefeArea
                + " WHERE departamento_id = " + id;
        
        try {
            conex.setQuery(sql);
            
            JOptionPane.showMessageDialog(this, "Departamento Actualizado Exitosamente");

            limpiarFormulario();
            
            listarDepartamentos();
        } catch (SQLException e) {
            System.out.println(e);
        }
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
        DepartamentosjTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnAccion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtDepartamento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbJefes = new javax.swing.JComboBox<>();

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

        DepartamentosjTable.setModel(new javax.swing.table.DefaultTableModel(
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
        DepartamentosjTable.setName("DepartamentosjTable"); // NOI18N
        DepartamentosjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DepartamentosjTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DepartamentosjTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento"));

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLabel5.setText("ID:");

        txtID.setEnabled(false);
        txtID.setName("txtID"); // NOI18N
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        btnAccion.setText("Ingresar");
        btnAccion.setEnabled(false);
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });

        jLabel1.setText("Departamento:");

        txtDepartamento.setName("txtDepartamento"); // NOI18N
        txtDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDepartamentoKeyPressed(evt);
            }
        });

        jLabel2.setText("Encargados disponibles: ");

        cmbJefes.setName("cmbJefes"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 79, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDepartamento)
                            .addComponent(cmbJefes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(btnAccion)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbJefes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAccion)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int id = Integer.parseInt(txtID.getText());

        borrarDepartamento(id);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        int error = 0;
        
        String departamento = txtDepartamento.getText();
        
        if (departamento.replace(" ", "").length() < 1) {
            JOptionPane.showMessageDialog(null, "Complete los datos correctamente", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
            error++;
        }
        
        if (error < 1) {
            int id = 0;
            
            for (int i = 0; i < jefes.size(); i++) {
                if (cmbJefes.getSelectedIndex() == Integer.parseInt(jefes.get(i).get(3))) {
                    id = Integer.parseInt(jefes.get(i).get(0));
                    break;
                } 
            }
            
            if (btnAccion.getText().equals("Ingresar")) {
                ingresoDatos(departamento, id);
            }
            
            if (btnAccion.getText().equals("Actualizar")) {
                actualizarDatos(departamento, id, Integer.parseInt(txtID.getText()));
            }
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    private void DepartamentosjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DepartamentosjTableMouseClicked
        int fila = DepartamentosjTable.rowAtPoint(evt.getPoint());

        for (int i = 0; i < departamentos.size(); i++) {
            if (fila == (Integer.parseInt(departamentos.get(i).get(5)) - 1)) {
                mostrarDatos(departamentos.get(i));
                break;
            }
        }
    }//GEN-LAST:event_DepartamentosjTableMouseClicked

    private void txtDepartamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepartamentoKeyPressed
        if (txtDepartamento.getText().replace(" ", "").length() > 0) {
            btnAccion.setEnabled(true);
        } else {
            btnAccion.setEnabled(false);
        }
    }//GEN-LAST:event_txtDepartamentoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DepartamentosjTable;
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbJefes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
