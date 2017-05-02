/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etlg;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author mike
 */
public class tableView extends JFrame{
    private databaseMDB database;
    private String tableName;
    private Table table;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton jButton1;
    Object[][] data;
    String[] columnNames;
    
    static String sqlStr = "CREATE DATABASE x;\n";
    /** Creates new form tableViewer */
    
    public tableView(){
        this.database = tableChooser.getDatabaseObject();
        this.tableName = tableChooser.jComboBox1.getSelectedItem().toString();
        this.database.setGenericTable(this.tableName);
        this.table = this.database.getGenericTable();
        setJackcessTable();
        initComponents();
    }
    
    private databaseMDB getMDBdatabase(){
        return this.database;
    }
    private String getTableName(){
        return this.tableName;
    }
    public Table getTable(){
        return this.table;
    }
  
    private void setJackcessTable(){
        int i = 0;
        int j;
        int columnNumber = this.table.getColumnCount();
        int rowNumber = this.table.getRowCount();
        this.columnNames = new String[columnNumber];
        String[] columnDataType = new String[columnNumber];
        this.data = new Object[rowNumber][columnNumber];
        for(Row row: this.table){
            j = 0;
            for(Column column : this.table.getColumns()) {
                this.columnNames[j] = column.getName().toString();
                this.data[i][j] = row.get(column.getName().toString());
                ++j;
            }
            ++i;
        }
    }
    
    
    private void makeTable(){
        sqlStr += "CREATE TABLE "+this.tableName+" (";
        for (Column column : this.table.getColumns()) {
            sqlStr += column.getName().toString()+" varchar(40),\n";
        }
        sqlStr = sqlStr.substring(0, sqlStr.length() - 1);
        sqlStr += ");";
    }
    
    private void makeInserts(){
        sqlStr += "INSERT INTO "+this.tableName+" VALUES\n";
        for (int i = 0; i < this.table.getRowCount(); i++) {
            sqlStr += "(";
            for (int j = 0; j < this.table.getColumnCount(); j++) {
                sqlStr += jTable1.getValueAt(i, j)+",";
            }
            sqlStr = sqlStr.substring( 0, sqlStr.length() - 1 );
            sqlStr += "),\n";
        }
        sqlStr = sqlStr.substring( 0, sqlStr.length() - 1 );
        sqlStr += ";";
    }
    private void jButton1ActionPerformed(ActionEvent evt) {
        makeTable();
        makeInserts();
        fileOpener.guardarArchivo(sqlStr);
        sqlStr = "";
    }
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton1.setText("Crear Tabla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(this.data, this.columnNames));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(426, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dbOpener.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dbOpener.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dbOpener.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dbOpener.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new tableView().setVisible(true);
            }
        });
    }
}
