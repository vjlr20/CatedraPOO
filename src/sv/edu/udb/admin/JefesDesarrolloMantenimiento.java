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
import sv.edu.udb.util.Validaciones;

public class JefesDesarrolloMantenimiento extends javax.swing.JInternalFrame {
    DefaultTableModel modeloMantenimiento = null;
    public static int bandera = 0;
    ResultSet resultado = null;
    
    Conexion conex = new Conexion();
    Validaciones validar = new Validaciones();
    
    ArrayList<ArrayList<String>> jefes = null;
    
    /**
     * Creates new form JefesDesarrolloMantenimiento
     */
    public JefesDesarrolloMantenimiento() throws SQLException {
        initComponents();
        
        bandera = 1;
        
        initComponents();
        
        Object[][] data = null;
        
        String[] columnas = { "#", "Nombre", "Usuario", "Correo", "Fecha de registro", "Estado" };
        
        modeloMantenimiento = new DefaultTableModel(data, columnas);
        this.JefesDesarrollojTable.setModel(modeloMantenimiento);
        
        listarJefesDesarrollo();
    }
    
    public void listarJefesDesarrollo() throws SQLException {
        jefes = new ArrayList<>();
        
        modeloMantenimiento.setRowCount(0);
        
        conex.setRs("SELECT usuarios.nombres, usuarios.apellidos, usuarios.usuario, usuarios.correo, DATE_FORMAT(usuarios.fecha_registro, '%d - %m -%Y') AS registro, estado_usuario.estado, usuarios.usuario_id FROM `usuarios` INNER JOIN estado_usuario ON usuarios.estado = estado_usuario.estado_usuario_id WHERE usuarios.tipo_usuario = 4");
        
        resultado = conex.getRs();
        
        int i = 0;

        while (resultado.next()) {
            i++;
            
            Object[] newRow = {
                i, (resultado.getString(1) + " " + resultado.getString(2)), resultado.getString(3), resultado.getString(4), resultado.getString(5), resultado.getString(6), 
            };
            
            ArrayList<String> jefe = new ArrayList<>();
            
            jefe.add(resultado.getString(7));
            jefe.add(resultado.getString(1));
            jefe.add(resultado.getString(2));
            jefe.add(resultado.getString(3));
            jefe.add(resultado.getString(4));
            jefe.add(resultado.getString(5));
            jefe.add(resultado.getString(6));
            jefe.add(String.valueOf(i));
            
            jefes.add(jefe);
            
            modeloMantenimiento.addRow(newRow);
        }
        
        resultado.close();
    }
    
    private void mostrarDatos(ArrayList<String> info) {
        for (String item : info) {
            System.out.println(item);
        }
        txtID.setText(info.get(0));
        txtNombres.setText(info.get(1));
        txtApellidos.setText(info.get(2));
        txtUsuario.setText(info.get(3));
        txtEmail.setText(info.get(4));
        
        if (info.get(6).equals("Activo")) {
            cmbEstado.setSelectedIndex(0);
        } else {
            cmbEstado.setSelectedIndex(1);
        }
        
        btnAccion.setText("Actualizar");
        btnAccion.setEnabled(true);
        
        btnEliminar.setEnabled(true);
    }
    
    private void limpiarFormulario() {
        txtID.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtUsuario.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        cmbEstado.setSelectedIndex(0);
        
        btnAccion.setText("Ingresar");
        btnAccion.setEnabled(false);
        
        btnEliminar.setEnabled(false);
    }
    
    private void ingresoDatos(String[] nuevoJefe) {
        String sql = "INSERT INTO usuarios (tipo_usuario, usuario, nombres, apellidos, correo, contraseña, estado) VALUES ("
                + "4, "
                + "'"+ nuevoJefe[2] +"', "
                + "'" + nuevoJefe[0] + "', "
                + "'" + nuevoJefe[1] + "', "
                + "'" + nuevoJefe[3] + "', "
                + "'" + nuevoJefe[4] + "', "
                + nuevoJefe[5] + ")";
        
        try {
            conex.setQuery(sql);
            
            JOptionPane.showMessageDialog(this, "Jefe de Desarrollo Ingresado Exitosamente");

            limpiarFormulario();
            
            listarJefesDesarrollo();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void borrarJefe(int id) {
        String sql = "DELETE FROM usuarios WHERE usuario_id = " + id;
        
        try {
            conex.setQuery(sql);
            
            JOptionPane.showMessageDialog(this, "Jefe de Desarrollo Eliminado Exitosamente");

            limpiarFormulario();
            
            listarJefesDesarrollo();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void actualizarDatos(String[] updateJefe, boolean password) {
        String sql;
        
        if (password) {
            sql = "UPDATE usuarios SET "
                    + "usuario = '" + updateJefe[3] + "', "
                    + "nombres = '" + updateJefe[1] + "', "
                    + "apellidos = '" + updateJefe[2] + "', "
                    + "correo = '" + updateJefe[4] + "', "
                    + "contraseña = '" + updateJefe[5] + "', "
                    + "estado = " + updateJefe[6] + ""
            + " WHERE usuario_id = " + updateJefe[0];   
        } else {
            sql = "UPDATE usuarios SET "
                    + "usuario = '" + updateJefe[3] + "', "
                    + "nombres = '" + updateJefe[1] + "', "
                    + "apellidos = '" + updateJefe[2] + "', "
                    + "correo = '" + updateJefe[4] + "', "
                    + "estado = " + updateJefe[5] + ""
            + " WHERE usuario_id = " + updateJefe[0];   
        }
        System.out.println(sql);
        try {
            conex.setQuery(sql);
            
            JOptionPane.showMessageDialog(this, "Jefe de Desarrollo Actualizado Exitosamente");

            limpiarFormulario();
            
            listarJefesDesarrollo();
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

        jRadioButton1 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JefesDesarrollojTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnAccion = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        jRadioButton1.setText("jRadioButton1");

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

        JefesDesarrollojTable.setModel(new javax.swing.table.DefaultTableModel(
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
        JefesDesarrollojTable.setName("JefesDesarrollojTable"); // NOI18N
        JefesDesarrollojTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JefesDesarrollojTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JefesDesarrollojTable);

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

        jLabel1.setText("Nombres:");

        txtNombres.setName("txtNombres"); // NOI18N

        jLabel2.setText("Apellidos:");

        txtApellidos.setName("txtApellidos"); // NOI18N

        jLabel3.setText("Contraseña:");

        txtUsuario.setName("txtUsuario"); // NOI18N

        jLabel4.setText("Correo:");

        txtEmail.setName("txtEmail"); // NOI18N

        jLabel5.setText("ID:");

        txtID.setEnabled(false);
        txtID.setName("txtID"); // NOI18N
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel6.setText("Usuario:");

        jLabel7.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cmbEstado.setName("cmbEstado"); // NOI18N

        btnAccion.setText("Ingresar");
        btnAccion.setEnabled(false);
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });

        txtPassword.setName("txtPassword"); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(147, 147, 147)
                                .addComponent(jLabel2)
                                .addGap(10, 10, 10)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEmail))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnNuevo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminar)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(155, 155, 155))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(btnAccion)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(135, 135, 135))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(95, 95, 95))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(btnAccion)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void JefesDesarrollojTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JefesDesarrollojTableMouseClicked
        int fila = JefesDesarrollojTable.rowAtPoint(evt.getPoint());
        
        for (int i = 0; i < jefes.size(); i++) {
            if (fila == (Integer.parseInt(jefes.get(i).get(7)) - 1)) {
                mostrarDatos(jefes.get(i));
                break;
            }
        }
    }//GEN-LAST:event_JefesDesarrollojTableMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        String[] data;
        int error = 0;
        
        int user = txtUsuario.getText().replace(" ", "").length();
        int name = txtNombres.getText().replace(" ", "").length();
        int lastname = txtApellidos.getText().replace(" ", "").length();
        int email = txtEmail.getText().replace(" ", "").length();
        
        if (btnAccion.getText().equals("Ingresar")) {
            int password = txtPassword.getText().replace(" ", "").length();
            
            if (user < 0 || name < 0 || lastname < 0 ||email < 0 || password < 0) {
                JOptionPane.showMessageDialog(null, "Complete los datos correctamente", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (password < 8) {
                JOptionPane.showMessageDialog(null, "La contraseña debe poseer un minimo de 8 caracteres", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (!validar.validarEmail(txtEmail.getText())) {
                JOptionPane.showMessageDialog(null, "Formato de correo incorrecto", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            } 
            
            if (error == 0) {
                data = new String[6];
                
                data[0] = txtNombres.getText();
                data[1] = txtApellidos.getText();
                data[2] = txtUsuario.getText();
                data[3] = txtEmail.getText();
                data[4] = txtPassword.getText();
                data[5] = String.valueOf(cmbEstado.getSelectedIndex() + 1);
                
                ingresoDatos(data);
            }
        }
        
        
        if (btnAccion.getText().equals("Actualizar")) {
            String id = txtID.getText();
            int password = txtPassword.getText().replace(" ", "").length();
            
            if (user < 0 || name < 0 || lastname < 0 ||email < 0) {
                JOptionPane.showMessageDialog(null, "Complete los datos correctamente", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (!validar.validarEmail(txtEmail.getText())) {
                JOptionPane.showMessageDialog(null, "Formato de correo incorrecto", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (password > 0) {
                if (password < 8) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe poseer un minimo de 8 caracteres", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                    error++;
                }
            }
            
            if (error == 0) {
                boolean newPassword;
                
                if (password >= 8) {
                    newPassword = true;
                    data = new String[7];
                    
                    data[0] = id;
                    data[1] = txtNombres.getText();
                    data[2] = txtApellidos.getText();
                    data[3] = txtUsuario.getText();
                    data[4] = txtEmail.getText();
                    data[5] = txtPassword.getText();
                    data[6] = String.valueOf(cmbEstado.getSelectedIndex() + 1);
                } else {
                    newPassword = false;
                            
                    data = new String[6];
                    
                    data[0] = id;
                    data[1] = txtNombres.getText();
                    data[2] = txtApellidos.getText();
                    data[3] = txtUsuario.getText();
                    data[4] = txtEmail.getText();
                    data[5] = String.valueOf(cmbEstado.getSelectedIndex() + 1);
                }
                
                
                actualizarDatos(data, newPassword);
            }
            
            System.out.println("Contraseña: " + password);
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        int user = txtUsuario.getText().replace(" ", "").length();
        int name = txtUsuario.getText().replace(" ", "").length();
        int lastname = txtUsuario.getText().replace(" ", "").length();
        int email = txtUsuario.getText().replace(" ", "").length();
        
        if (btnAccion.getText().equals("Ingresar")) {
            int password = txtPassword.getText().replace(" ", "").length();
            
            if (user > 0 && name > 0 && lastname > 0 && email > 0 && password > 0) {
                btnAccion.setEnabled(true);
            } else {
                btnAccion.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int id = Integer.parseInt(txtID.getText());
        
        borrarJefe(id);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        try {
            bandera = 0;

            this.dispose();

            conex.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex);
            // Logger.getLogger(AlumnosMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JefesDesarrollojTable;
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
