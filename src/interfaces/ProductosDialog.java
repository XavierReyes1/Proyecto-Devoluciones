/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package interfaces;
import Clases.DatabaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author Axel Reyes
 */
public class ProductosDialog extends javax.swing.JDialog {

    private DefaultTableModel modeloTabla;
    private JTextField txtProducto, txtNombre, txtProveedor;



    public ProductosDialog(JFrame parent, JTextField txtProducto, JTextField txtNombre, JTextField txtProveedor) {
        super(parent, "Seleccionar Producto", true);
        this.txtProducto = txtProducto;
        this.txtNombre = txtNombre;
        this.txtProveedor = txtProveedor;

        setSize(600, 400);
        setLocationRelativeTo(parent);

        // Crear el panel superior con el buscador
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new FlowLayout(FlowLayout.LEFT));

        txtBuscar = new JTextField(20); // Campo de texto para buscar
        panelBuscar.add(new JLabel("Buscar por nombre:"));
        panelBuscar.add(txtBuscar);

        // Crear la tabla
          modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que todas las celdas no sean editables
                return false;
            }
        };
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Proveedor");

        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        // Agregar componentes al diálogo
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBuscar, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Cargar todos los productos al iniciar
        cargarProductos("");

        // Agregar DocumentListener al campo de búsqueda
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarProductos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarProductos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarProductos();
            }

            private void buscarProductos() {
                String nombre = txtBuscar.getText().trim();
                cargarProductos(nombre); // Cargar productos que coincidan con el nombre
            }
        });

        // Agregar listener para doble clic en la tabla
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    seleccionarProducto();
                }
            }
        });
    }

    private void cargarProductos(String nombre) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return;

        // Limpiar la tabla antes de cargar nuevos datos
        modeloTabla.setRowCount(0);

        try {
            String sql = "SELECT p.id_producto, p.nombre_producto, pr.nombre_proveedor " +
                         "FROM productos p " +
                         "JOIN proveedores pr ON p.id_proveedor = pr.id_proveedor " +
                         "WHERE p.nombre_producto LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nombre + "%"); // Buscar productos que contengan el nombre
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("nombre_proveedor")
                });
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void seleccionarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            txtProducto.setText(tablaProductos.getValueAt(filaSeleccionada, 0).toString());
            txtNombre.setText(tablaProductos.getValueAt(filaSeleccionada, 1).toString());
            txtProveedor.setText(tablaProductos.getValueAt(filaSeleccionada, 2).toString());
            dispose(); // Cerrar el diálogo después de seleccionar
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
        tablaProductos = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProductos.setEditingColumn(0);
        tablaProductos.setEditingRow(0);
        jScrollPane1.setViewportView(tablaProductos);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        
        String nombre = txtBuscar.getText().trim();
                cargarProductos(nombre); 
    }//GEN-LAST:event_txtBuscarKeyTyped

    /**
     * @param args the command line arguments
     */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(ProductosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(ProductosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(ProductosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(ProductosDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the dialog */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Crear campos de texto ficticios para probar el diálogo
            JTextField txtProducto = new JTextField();
            JTextField txtNombre = new JTextField();
            JTextField txtProveedor = new JTextField();

            ProductosDialog dialog = new ProductosDialog(new javax.swing.JFrame(), txtProducto, txtNombre, txtProveedor);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
