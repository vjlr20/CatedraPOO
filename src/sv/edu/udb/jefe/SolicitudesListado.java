/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.jefe;

import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.db.Conexion;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import sun.security.krb5.internal.rcache.DflCache;

/**
 *
 * @author Victor López
 */
public class SolicitudesListado extends javax.swing.JInternalFrame {

    // @Victor5032 que ondas
    /**
     * Creates new form SolicitudesListado
     */
//    String para guardas Id en base a la seleccion del combo
    String departamentoId = "";
    String tipoSolicitudid = "";
    String estadoId = "";
//    data chooser
    private JDateChooser date;
//    Modelo de tablas
    DefaultTableModel listadoSolictudesModel = new DefaultTableModel();
//    edicion de Solicitudes
    String solicitud = "";
//    conexiones
    ResultSet rs = null;
    Conexion conexionDB = new Conexion();
    public static int bandera = 0;

    public SolicitudesListado() throws SQLException {
        bandera = 1;
        initComponents();
//        metodos llenado de combobox
        llenadoDepartamentos();
        llenadoTipoSolicitud();
        llenadoEstadoDeCaso();
//        fin de llamado de metodos
//       metodo llenado de Jtable
        llenadoListadoSolicitudes();
    }
//    llenado de las diferentes
//    combobox 

//    llenado de comboBox Departamentos
    public void llenadoDepartamentos() {
        try {
            conexionDB.setRs("SELECT * FROM departamento");
            ArrayList<String> departamentos = new ArrayList<>();
            rs = conexionDB.getRs();
            while (rs.next()) {
                departamentos.add(rs.getString(2));
            }
//        llenamos nuestro JcomBoxDepartamentos con los departamentos disponibles.
            jComboBoxDepartamentos.setModel(new DefaultComboBoxModel<String>(departamentos.toArray(new String[0])));
            jComboBoxNewDepa.setModel(new DefaultComboBoxModel<String>(departamentos.toArray(new String[0])));
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    llenado de ComboBox tipo de solicitud
    public void llenadoTipoSolicitud() {
        try {
            conexionDB.setRs("SELECT * FROM tipo_solicitud");
            ArrayList<String> Solicitud = new ArrayList<>();
            rs = conexionDB.getRs();
            while (rs.next()) {
                Solicitud.add(rs.getString(2));
            }
//            llenado de comboboxSolicitud
            jComboBoxSolicitud.setModel(new DefaultComboBoxModel<String>(Solicitud.toArray(new String[0])));
            jComboBoxNewTipo.setModel(new DefaultComboBoxModel<String>(Solicitud.toArray(new String[0])));
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    llenado de ComboBox Estado

    public void llenadoEstadoDeCaso() {
        try {
            conexionDB.setRs("SELECT * FROM estado_solicitud");
            ArrayList<String> estadoCaso = new ArrayList<>();
            rs = conexionDB.getRs();
            while (rs.next()) {
                estadoCaso.add(rs.getString(2));
            }
            jComboBoxEstado.setModel(new DefaultComboBoxModel<String>(estadoCaso.toArray(new String[0])));
            jComboBoxNewEstado.setModel(new DefaultComboBoxModel<String>(estadoCaso.toArray(new String[0])));
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    Fin de metodos de llenado del los combo
//    ***************************************
//    llenado de JtableListaSolicitudes

    public void llenadoListadoSolicitudes() {

        try {
            Object[][] data = null;
            String[] columna = {
                "ID", "Caso", "Descripcion", "Observaciones", "Fecha de registro"
            };
            listadoSolictudesModel = new DefaultTableModel(data, columna);
            this.jTableListado.setModel(listadoSolictudesModel);
            conexionDB.setRs("SELECT * FROM solicitud");
            rs = conexionDB.getRs();
            while (rs.next()) {
                Object[] nuevaColumna = {rs.getInt(1), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),};
                listadoSolictudesModel.addRow(nuevaColumna);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTituloSolicitud = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacion = new javax.swing.JTextArea();
        jComboBoxDepartamentos = new javax.swing.JComboBox<>();
        jComboBoxSolicitud = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButtonCrearNuevaSolicitud = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableListado = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabelSoliName = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldTituloEdit = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabelDepartamentoActu = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxNewDepa = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabelTipoActu = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxNewTipo = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDescripup = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaObservacionesUp = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel1EstadoActu = new javax.swing.JLabel();
        jComboBoxNewEstado = new javax.swing.JComboBox<>();
        jButtonActualizar = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Solicitudes.");
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Titulo "));

        jLabel1.setText("Ingrese titulo de la nueva solicitud : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jTextFieldTituloSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTituloSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Descripción : ");

        jLabel3.setText("Observaciones: ");

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescripcion);

        jTextAreaObservacion.setColumns(20);
        jTextAreaObservacion.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacion);

        jLabel4.setText("Asigne un departamento : ");

        jLabel5.setText("Tipo de solicitud : ");

        jLabel6.setText("Estado :");

        jButtonCrearNuevaSolicitud.setText("Crear nueva solicitud");
        jButtonCrearNuevaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearNuevaSolicitudActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxEstado, 0, 238, Short.MAX_VALUE)
                            .addComponent(jComboBoxSolicitud, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jButtonCrearNuevaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel2)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButtonCrearNuevaSolicitud)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Crear nueva solicitud", jPanel1);

        jLabel8.setText("Solicitudes actuales");

        jTableListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", ""
            }
        ));
        jTableListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListadoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableListado);

        jLabel9.setText("Solicitud a editar : ");

        jLabel10.setText("Editar Titulo : ");

        jLabel11.setText("Departamento actual: ");

        jLabelDepartamentoActu.setText("_______");

        jLabel12.setText("Nuevo : ");

        jLabel13.setText("Tipo de solicitud actual: ");

        jLabelTipoActu.setText("_______");

        jLabel14.setText("Nuevo :");

        jLabel15.setText("Editar descripción ");

        jTextAreaDescripup.setColumns(20);
        jTextAreaDescripup.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDescripup);

        jLabel16.setText("Editar observaciones ");

        jTextAreaObservacionesUp.setColumns(20);
        jTextAreaObservacionesUp.setRows(5);
        jScrollPane5.setViewportView(jTextAreaObservacionesUp);

        jLabel17.setText("Estado actual : ");

        jLabel1EstadoActu.setText("____");

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Eliminar");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(613, 613, 613)
                            .addComponent(jTextFieldTituloEdit))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel15))
                                    .addGap(52, 52, 52)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane5)
                                        .addComponent(jScrollPane3)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel1EstadoActu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxNewEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(167, 167, 167)
                                            .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(163, 163, 163)
                                            .addComponent(jLabelSoliName, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(61, 61, 61)
                                    .addComponent(jButtonDelete)
                                    .addGap(0, 1, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel13))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabelTipoActu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabelDepartamentoActu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel14))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxNewDepa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxNewTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(45, 45, 45))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabelSoliName)
                            .addComponent(jButtonDelete))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldTituloEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDepartamentoActu)
                            .addComponent(jLabel12)
                            .addComponent(jComboBoxNewDepa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabelTipoActu)
                            .addComponent(jLabel14)
                            .addComponent(jComboBoxNewTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel1EstadoActu)
                            .addComponent(jComboBoxNewEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonActualizar))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Mantenimiento solicitudes", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCrearNuevaSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearNuevaSolicitudActionPerformed
        // TODO add your handling code here:
//        varuables:
//        comboBox variables: 
        String departamento = "";
        String tipoSolicitud = "";
        String estadosolicitud = "";
//        comprovacion de campos vacios
//      obtencion de fecha actual
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if (jTextFieldTituloSolicitud.getText().trim().isEmpty() || jTextAreaDescripcion.getText().trim().isEmpty() || jTextAreaObservacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, no dejar campos vacios");
        } else {
            try {
                //            recogemos el valor de los Jcombobox
                departamento = jComboBoxDepartamentos.getSelectedItem().toString();
                tipoSolicitud = jComboBoxSolicitud.getSelectedItem().toString();
                estadosolicitud = jComboBoxEstado.getSelectedItem().toString();

//           obtenemos el id para departamento
                conexionDB.setRs("SELECT * FROM departamento WHERE departamento = \"" + departamento + "\"");
                rs = conexionDB.getRs();
                if (rs.next()) {
                    departamentoId = Integer.toString(rs.getInt(1));
                }
                conexionDB.setRs("SELECT * FROM tipo_solicitud WHERE tipo = \"" + tipoSolicitud + "\"");
                rs = conexionDB.getRs();
                if (rs.next()) {
                    tipoSolicitudid = Integer.toString(rs.getInt(1));
                }
                conexionDB.setRs("SELECT * FROM estado_solicitud WHERE estado = \"" + estadosolicitud + "\"");
                rs = conexionDB.getRs();
                if (rs.next()) {
                    estadoId = Integer.toString(rs.getInt(1));
                }
//                fin de optencion de IDS de los ComboBox
//                Ejecucion de Query
                conexionDB.setQuery("INSERT INTO solicitud (solicitud_id, departamento_id, tipo_solicitud, caso, descripcion, observaciones, fecha_registro, estado) VALUES (NULL, \"" + departamentoId + "\",\"" + tipoSolicitudid + "\",\"" + jTextFieldTituloSolicitud.getText() + "\",\"" + jTextAreaDescripcion.getText() + "\",\"" + jTextAreaObservacion.getText() + "\",\"" + dtf.format(now) + "\",\"" + estadoId + "\")");
                JOptionPane.showMessageDialog(this, "su solicitud se ha procesado con éxito el " + dtf.format(now) );
                llenadoListadoSolicitudes();
            } catch (SQLException ex) {
                Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButtonCrearNuevaSolicitudActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        try {
            // TODO add your handling code here:
            bandera = 0;
            this.dispose();
            conexionDB.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formInternalFrameClosing
//    obtener datos segun click en tabla
    private void jTableListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListadoMouseClicked
        // TODO add your handling code here:
//        variables
        String solicitudTitulo = "";
        String descripcion = "";
        String observacion = "";
        int fila = jTableListado.rowAtPoint(evt.getPoint());
        int columna = jTableListado.columnAtPoint(evt.getPoint());
        if ((fila > -1) && (columna > -1)) {
            switch (columna) {
                case 0:
                    solicitud = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna));
                    solicitudTitulo = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna + 1));
                    descripcion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna + 2));
                    observacion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna + 3));
                    break;
                case 1:
                    solicitud = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 1));
                    solicitudTitulo = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna));
                    descripcion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna + 1));
                    observacion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna + 2));
                    break;
                case 2:
                    solicitud = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 2));
                    solicitudTitulo = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 1));
                    descripcion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna));
                    observacion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna + 1));
                    break;
                case 3:
                    solicitud = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 3));
                    solicitudTitulo = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 2));
                    descripcion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 1));
                    observacion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna));
                    break;
                case 4:
                    solicitud = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 4));
                    solicitudTitulo = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 3));
                    descripcion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 2));
                    observacion = String.valueOf(listadoSolictudesModel.getValueAt(fila, columna - 1));
                    break;
                default:
                    System.out.println("Somethin went wrong");
            }
            jLabelSoliName.setText(solicitudTitulo);
            jTextFieldTituloEdit.setText(solicitudTitulo);
            jTextAreaDescripup.setText(descripcion);
            jTextAreaObservacionesUp.setText(observacion);
            llenadoSegunRelaciones(solicitud);
        }
    }//GEN-LAST:event_jTableListadoMouseClicked

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
//        comprovamos que tenemos que eliminar
        if (jLabelSoliName.getText() == "") {
            JOptionPane.showMessageDialog(this, "Seleccione una solictud desde la tabla, Puede hacerlo mediante click");
        } else {
            int ask = JOptionPane.showConfirmDialog(this, "Desea eliminar la Solicitud, " + jLabelSoliName.getText() + "?");
            if (ask == JOptionPane.YES_OPTION) {
                try {
                    conexionDB.setQuery("SET foreign_key_checks = 0;");
                    conexionDB.setQuery("DELETE FROM solicitud WHERE solicitud_id = " + solicitud + "");
                    conexionDB.setQuery("SET foreign_key_checks = 1;");
                    JOptionPane.showMessageDialog(this, "su solicitud se ha eliminado con éxito");
                    resetTextFields();
                    llenadoListadoSolicitudes();
                } catch (SQLException ex) {
                    Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        try {
            // TODO add your handling code here:
//        variables
            String newdeparamento = "";
            String newtipo = "";
            String newestado = "";
//        nuevas relaciones
            int idnewdepartamenrto = 0;
            int idnewtipo = 0;
            int idnewestado = 0;
//        llenado de varibales atrabes de los jtexbox
            newdeparamento = jComboBoxNewDepa.getSelectedItem().toString();
            newtipo = jComboBoxNewTipo.getSelectedItem().toString();
            newestado = jComboBoxNewEstado.getSelectedItem().toString();
//      obtecion del id de cada uno de ellos
            conexionDB.setRs("SELECT * FROM departamento WHERE departamento = \"" + newdeparamento + "\"");
            rs = conexionDB.getRs();
            if (rs.next()) {
                idnewdepartamenrto = rs.getInt(1);
            }
            conexionDB.setRs("SELECT * FROM tipo_solicitud WHERE tipo = \"" + newtipo + "\"");
            rs = conexionDB.getRs();
            if (rs.next()) {
                idnewtipo = rs.getInt(1);
            }
            conexionDB.setRs("SELECT * FROM estado_solicitud WHERE estado = \"" + newestado + "\"");
            rs = conexionDB.getRs();
            if (rs.next()) {
                idnewestado = rs.getInt(1);
            }
//       executamos query update
            conexionDB.setQuery("UPDATE solicitud set departamento_id =\""+idnewdepartamenrto+"\""+", tipo_solicitud = \""+idnewtipo+"\""+", caso = \""+jTextFieldTituloEdit.getText()+"\""+", descripcion = \""+jTextAreaDescripup.getText()+"\""+", observaciones = \""+jTextAreaObservacionesUp.getText()+"\""+", estado = \""+idnewestado+"\""+" WHERE solicitud_id ="+solicitud+"" );
            JOptionPane.showMessageDialog(this, "su solicitud se ha modificado con éxito");
            resetTextFields();
            llenadoListadoSolicitudes();
             
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonActualizarActionPerformed

    public void resetTextFields(){
        jTextFieldTituloEdit.setText("");
        jTextAreaDescripup.setText("");
        jTextAreaObservacionesUp.setText("");
        jLabelDepartamentoActu.setText("____");
        jLabel1EstadoActu.setText("____");
        jLabel1EstadoActu.setText("____");
        jLabelSoliName.setText("");
        
    }
//    metodo de llenado segun las relaciones
    public void llenadoSegunRelaciones(String id) {
        try {
            int idRelaciones = Integer.parseInt(id);
//       variables para cada realcion
            int departmanetoAsignado = 0;
            int tipoAsinado = 0;
            int estadoCasoAsignado = 0;

            String departmanento = "";
            String tipo = "";
            String estado = "";
//
            conexionDB.setRs("SELECT * FROM solicitud WHERE solicitud_id = " + idRelaciones + "");
            rs = conexionDB.getRs();
            if (rs.next()) {
                departmanetoAsignado = rs.getInt(2);
                tipoAsinado = rs.getInt(3);
                estadoCasoAsignado = rs.getInt(8);
            }
//            llenado de labels con el valor relacionado a cambiar
//            1' departamento
            conexionDB.setRs("SELECT * FROM departamento WHERE departamento_id = " + departmanetoAsignado + "");
            rs = conexionDB.getRs();
            if (rs.next()) {
                departmanento = rs.getString(2);
            }
//            2' tipo
            conexionDB.setRs("SELECT * FROM tipo_solicitud WHERE tipo_solicitud_id = " + tipoAsinado + "");
            rs = conexionDB.getRs();
            if (rs.next()) {
                tipo = rs.getString(2);
            }
//            3' estado_solicitud
            conexionDB.setRs("SELECT * FROM estado_solicitud WHERE estado_solicitud_id = " + estadoCasoAsignado + "");
            rs = conexionDB.getRs();
            if (rs.next()) {
                estado = rs.getString(2);
            }
            jLabelDepartamentoActu.setText(departmanento);
            jLabelTipoActu.setText(tipo);
            jLabel1EstadoActu.setText(estado);
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudesListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonCrearNuevaSolicitud;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JComboBox<String> jComboBoxDepartamentos;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxNewDepa;
    private javax.swing.JComboBox<String> jComboBoxNewEstado;
    private javax.swing.JComboBox<String> jComboBoxNewTipo;
    private javax.swing.JComboBox<String> jComboBoxSolicitud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel1EstadoActu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDepartamentoActu;
    private javax.swing.JLabel jLabelSoliName;
    private javax.swing.JLabel jLabelTipoActu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableListado;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JTextArea jTextAreaDescripup;
    private javax.swing.JTextArea jTextAreaObservacion;
    private javax.swing.JTextArea jTextAreaObservacionesUp;
    private javax.swing.JTextField jTextFieldTituloEdit;
    private javax.swing.JTextField jTextFieldTituloSolicitud;
    // End of variables declaration//GEN-END:variables
}
