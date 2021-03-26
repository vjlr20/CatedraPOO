/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.desarrollo;

/**
 *
 * @author Victor López
 */

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.db.Conexion;

public class CasoListado extends javax.swing.JInternalFrame {
    DefaultTableModel modeloCasos = null;
    public static int bandera = 0;
    ResultSet resultado = null;
    
    Conexion conex = new Conexion();
    
    ArrayList<ArrayList<String>> casos = null;
    ArrayList<ArrayList<String>> programadores = null;
    ArrayList<ArrayList<String>> testers = null;
    
    /**
     * Creates new form CasoListado
     */
    public CasoListado() throws SQLException {
        initComponents();
        Date date = new Date();
        
        limitejDate.setDate(date);
        
        limitejDate.setMinSelectableDate(date);
                
        bandera = 1;
        
        Object[][] data = null;
        
        String[] columnas = { "#", "Tipo", "Departamento", "Caso", "Descripción", "Fecha de registro" };
        
        modeloCasos = new DefaultTableModel(data, columnas);
        this.casosJTable.setModel(modeloCasos);
        
        listarCasosEspera();
        
        comboProgramador();
    }
    
    private void comboProgramador() throws SQLException {
        programadores = new ArrayList<>();
        
        String sql = "SELECT * FROM usuarios WHERE tipo_usuario = 5 AND estado = 1";
        
        conex.setRs(sql);
        
        ResultSet rs = conex.getRs();
        
        cmbProgramador.removeAllItems();
        
        int i = 0;
        
        while (rs.next()) {
            String persona = rs.getString(4) + " " + rs.getString(5);
            
            cmbProgramador.addItem(persona);
            
            ArrayList<String> programador = new ArrayList<>();
            
            programador.add(rs.getString(1));
            programador.add(rs.getString(3));
            programador.add(rs.getString(4));
            programador.add(rs.getString(5));
            programador.add(String.valueOf(i));
            
            programadores.add(programador);
            
            i++;
        }
        
        rs.close();
    }
    
    private void comboTester(String area) throws SQLException {
        testers = new ArrayList<>();
        
        String sql = "SELECT usuarios.usuario_id, usuarios.nombres, usuarios.apellidos FROM departamento_empleados INNER JOIN usuarios ON usuarios.usuario_id = departamento_empleados.empleado WHERE departamento_empleados.departamento = " + area + " AND usuarios.estado = 1";
        
        conex.setRs(sql);
        
        ResultSet rs = conex.getRs();
        
        cmbTester.removeAllItems();
        
        int i = 0;
        
        while (rs.next()) {
            String persona = rs.getString(2) + " " + rs.getString(3);
            
            cmbTester.addItem(persona);
            
            ArrayList<String> tester = new ArrayList<>();
            
            tester.add(rs.getString(1));
            tester.add(rs.getString(2));
            tester.add(rs.getString(3));
            tester.add(String.valueOf(i));
            
            testers.add(tester);
            
            i++;
        }
        
        rs.close();
    }
    
    private void listarCasosEspera() throws SQLException {
        casos = new ArrayList<>();
        
        modeloCasos.setRowCount(0);
        
        String sql = "SELECT solicitud.solicitud_id, tipo_solicitud.tipo, departamento.departamento, solicitud.caso, solicitud.descripcion, solicitud.fecha_registro, solicitud.departamento_id FROM solicitud INNER JOIN tipo_solicitud ON solicitud.tipo_solicitud = tipo_solicitud.tipo_solicitud_id INNER JOIN departamento ON solicitud.departamento_id = departamento.departamento_id WHERE solicitud.estado = 3 ORDER BY solicitud.fecha_registro DESC";
        
        conex.setRs(sql);
        
        resultado = conex.getRs();
        
        int i = 0;

        while (resultado.next()) {
            i++;
            
            Object[] newRow = {
                resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4), resultado.getString(5), resultado.getString(6),
            };
            
            ArrayList<String> caso = new ArrayList<>();
            
            caso.add(resultado.getString(1));
            caso.add(resultado.getString(2));
            caso.add(resultado.getString(3));
            caso.add(resultado.getString(4));
            caso.add(resultado.getString(5));
            caso.add(resultado.getString(6));
            caso.add(resultado.getString(7));
            caso.add(String.valueOf(i));
            
            casos.add(caso);
            
            modeloCasos.addRow(newRow);
        }

        resultado.close();
    }
    
    private void limpiarDatos() {
        lblID.setText("_________");
        lblDepartamento.setText("____");
        lblTipo.setText("____");
        lblCaso.setText("________________________________");
        txtDescripcion.setText("");
        txtAdicionales.setText("");
        txtObservaciones.setText("");
        lblRegistro.setText("_________");
        
        limitejDate.setDate(new Date());
        
        cmbTester.removeAllItems();
        
        btnAceptar.setEnabled(true);
    }
    
    private void mostrarDatos(ArrayList<String> info) throws SQLException {
        /*for (String item : info) {
            System.out.println(item);
        }*/
        
        lblID.setText(info.get(0));
        lblDepartamento.setText(info.get(2));
        lblTipo.setText(info.get(1));
        lblCaso.setText(info.get(3));
        txtDescripcion.setText(info.get(4));
        lblRegistro.setText(info.get(5));
        
        comboTester(info.get(6));
        
        btnAceptar.setEnabled(true);
    }
    
    private int obtenerProgramador(int index) {
        int response = 0;
        
        for (int i = 0; i < programadores.size(); i++) {
            if (index == Integer.parseInt(programadores.get(i).get(4))) {
                response = Integer.parseInt(programadores.get(i).get(0));
                break;
            }
        }
        
        return response;
    }
    
    private int obtenerTester(int index) {
        int response = 0;
        
        for (int i = 0; i < testers.size(); i++) {
            if (index == Integer.parseInt(testers.get(i).get(3))) {
                response = Integer.parseInt(testers.get(i).get(0));
                break;
            }
        }
        
        return response;
    }
    
    private String generarCodigo(int solicitud, String fechaCaso) throws SQLException {
        String response = "";
        
        String query = "SELECT departamento.codigo, DATE_FORMAT(solicitud.fecha_registro, '%Y%m%d') AS fecha FROM solicitud INNER JOIN departamento ON departamento.departamento_id = solicitud.departamento_id WHERE solicitud.solicitud_id = " + solicitud;
        // System.out.println(query);
        conex.setRs(query);
        
        ResultSet result = conex.getRs();
        
        result.last();
        
        if (result.getRow() > 0) {
           response = result.getString(1) + result.getString(2) + "-" + ThreadLocalRandom.current().nextInt(100, 999) + "-"  + fechaCaso + "-" + ThreadLocalRandom.current().nextInt(100, 999);
            
        }
        
        return response;
    }
    
    private void aceptarSolicitud(int solicitud, String notas, int programador, int tester, String fechaLimite, String fechaCaso) throws SQLException {
        String codigo = generarCodigo(solicitud, fechaCaso);
        /*System.out.println(codigo);
        System.out.println(solicitud);
        System.out.println(notas);
        System.out.println(programador);
        System.out.println(tester);
        System.out.println(fechaLimite);*/
        String query1 = "UPDATE solicitud SET estado = 1 WHERE solicitud_id = " + solicitud;
        
        conex.setQuery(query1);
        
        String query2 = "INSERT INTO caso (codigo, solicitud_id, caso_descripcion, programador, fecha_limite, tester, estado) VALUES ("
                + "'" + codigo + "', "
                + solicitud + ", "
                + "'" + notas + "',"
                + programador + ", "
                + "'" + fechaLimite + "', "
                + tester + ", "
                + "5)";
        
        conex.setQuery(query2);
        
        JOptionPane.showMessageDialog(this, "Caso asignado");
        
        limpiarDatos();
        listarCasosEspera();
        
        btnAceptar.setEnabled(false);
    }
    
    private void rechazarSolicitud(int solicitud, String observaciones) throws SQLException {
        String query = "UPDATE solicitud SET estado = 2, observaciones = '" + observaciones + "' WHERE solicitud_id = " + solicitud;
        
        conex.setQuery(query);
        
        JOptionPane.showMessageDialog(this, "Caso rechazado");
        
        limpiarDatos();
        listarCasosEspera();
        
        btnAceptar.setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accionesBtg = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        casosJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCaso = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        lblRegistro = new javax.swing.JLabel();
        rbdAprobrar = new javax.swing.JRadioButton();
        rbdRechazar = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAdicionales = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        cmbProgramador = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cmbTester = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        limitejDate = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();

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
        casosJTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        casosJTable.setName("casosJTable"); // NOI18N
        casosJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                casosJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(casosJTable);

        jLabel1.setText("Buscar:");

        txtBuscar.setName("txtBuscar"); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setText("ID:");

        lblID.setText("_________");
        lblID.setToolTipText("");

        jLabel4.setText("Caso:");

        lblCaso.setText("________________________________");

        jLabel6.setText("Departamento:");

        lblDepartamento.setText("____");

        jLabel8.setText("Tipo:");

        lblTipo.setText("____");

        jLabel10.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setEnabled(false);
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel11.setText("Registrada:");

        lblRegistro.setText("_________");

        accionesBtg.add(rbdAprobrar);
        rbdAprobrar.setText("Aprobar");
        rbdAprobrar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbdAprobrarStateChanged(evt);
            }
        });

        accionesBtg.add(rbdRechazar);
        rbdRechazar.setText("Rechazar");
        rbdRechazar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbdRechazarStateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setText("Nota adicional:");

        txtAdicionales.setColumns(20);
        txtAdicionales.setRows(5);
        txtAdicionales.setEnabled(false);
        jScrollPane4.setViewportView(txtAdicionales);

        jLabel15.setText("Programador:");

        cmbProgramador.setEnabled(false);

        jLabel16.setText("Tester:");

        cmbTester.setEnabled(false);

        jLabel17.setText("Fecha limite:");

        limitejDate.setEnabled(false);
        limitejDate.setMinSelectableDate(new java.util.Date(-62135744311000L));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(limitejDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(4, 4, 4)
                        .addComponent(cmbProgramador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbProgramador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbTester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(limitejDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        txtObservaciones.setEnabled(false);
        jScrollPane3.setViewportView(txtObservaciones);

        jLabel13.setText("Observaciones:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblID))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(rbdAprobrar)
                                .addGap(94, 94, 94)
                                .addComponent(rbdRechazar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(lblCaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(199, 199, 199))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRegistro)
                        .addGap(165, 165, 165))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblID))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblCaso))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblDepartamento)
                    .addComponent(jLabel8)
                    .addComponent(lblTipo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblRegistro))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbdAprobrar)
                    .addComponent(rbdRechazar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        while (modeloCasos.getRowCount() != 0) modeloCasos.removeRow(0) ;

        String busqueda = txtBuscar.getText();

        String sql = "SELECT solicitud.solicitud_id, tipo_solicitud.tipo, departamento.departamento, solicitud.caso, solicitud.descripcion, solicitud.fecha_registro FROM solicitud INNER JOIN tipo_solicitud ON solicitud.tipo_solicitud = tipo_solicitud.tipo_solicitud_id INNER JOIN departamento ON solicitud.departamento_id = departamento.departamento_id WHERE solicitud.estado = 3 AND solicitud.caso LIKE '%" + busqueda + "%' ORDER BY solicitud.fecha_registro DESC";

        conex.setRs(sql);

        try {
            listarCasosEspera();
        } catch (SQLException ex) {
            System.out.println(ex);
            // Logger.getLogger(AlumnosListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void rbdAprobrarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbdAprobrarStateChanged
        if (rbdAprobrar.isSelected()) {
            txtAdicionales.setEnabled(true);
            cmbProgramador.setEnabled(true);
            cmbTester.setEnabled(true);
            limitejDate.setEnabled(true);
        } else {
            txtObservaciones.setEnabled(false);
        }
    }//GEN-LAST:event_rbdAprobrarStateChanged

    private void rbdRechazarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbdRechazarStateChanged
        if (rbdRechazar.isSelected()) {
            txtObservaciones.setEnabled(true);
        } else {
            txtAdicionales.setEnabled(false);
            cmbProgramador.setEnabled(false);
            cmbTester.setEnabled(false);
            limitejDate.setEnabled(false);
        }
    }//GEN-LAST:event_rbdRechazarStateChanged

    private void casosJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_casosJTableMouseClicked
        int fila = casosJTable.rowAtPoint(evt.getPoint());
        
        for (int i = 0; i < casos.size(); i++) {
            if (fila == (Integer.parseInt(casos.get(i).get(7)) - 1)) {
                try {
                    mostrarDatos(casos.get(i));
                } catch (SQLException ex) {
                    Logger.getLogger(CasoListado.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }//GEN-LAST:event_casosJTableMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        int error = 0;
        
        int solicitud = Integer.parseInt(lblID.getText());
        
        if (rbdAprobrar.isSelected()) {
            String notasAdicionales = txtAdicionales.getText();
            int tester = cmbTester.getSelectedIndex();
            int programador = cmbProgramador.getSelectedIndex();
            
            String limite, fechaCaso;
            
            try {
                limite = new SimpleDateFormat("yyyy-MM-dd").format(limitejDate.getDate());
                fechaCaso = new SimpleDateFormat("yy").format(limitejDate.getDate());
            } catch (Exception e) {
                limite = null;
                fechaCaso = null;
            }            
            
            if (limite == null) {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha valida", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (notasAdicionales.replace(" ", "").length() < 1) {
                JOptionPane.showMessageDialog(null, "Ingrese información adicional del caso", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (error < 1) {
                int programadorId = obtenerProgramador(programador);
                int testerId = obtenerTester(tester);
                
                try {
                    aceptarSolicitud(solicitud, notasAdicionales, programadorId, testerId, limite, fechaCaso);
                } catch (SQLException ex) {
                    Logger.getLogger(CasoListado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (rbdRechazar.isSelected()) {
            String observaciones = txtObservaciones.getText();
            
            if (observaciones.replace(" ", "").length() < 1) {
                JOptionPane.showMessageDialog(null, "Ingresea las observaciones para rechazar el caso", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                error++;
            }
            
            if (error < 1) {
                try {
                    rechazarSolicitud(solicitud, observaciones);
                } catch (SQLException ex) {
                    Logger.getLogger(CasoListado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup accionesBtg;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JTable casosJTable;
    private javax.swing.JComboBox<String> cmbProgramador;
    private javax.swing.JComboBox<String> cmbTester;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCaso;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JLabel lblTipo;
    private com.toedter.calendar.JDateChooser limitejDate;
    private javax.swing.JRadioButton rbdAprobrar;
    private javax.swing.JRadioButton rbdRechazar;
    private javax.swing.JTextArea txtAdicionales;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
