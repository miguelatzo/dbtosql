/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlmc;

import static etlmc.NewDialogTB.tableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nwni
 */
public class NewDialogTBEmptyCells extends NewDialogTB{
//Se crea un nuevo frame SOLO CON UNA TABLA!

    static final NewDialogTBEmptyCells objNewDialog1 = new NewDialogTBEmptyCells();

    //static String[] tableDoctor = {"No Identificador","Especialidad"};
    static DefaultTableModel tableModel1;
    private final Border outside = new MatteBorder(1, 0, 1, 0, Color.RED);
    private final Border inside = new EmptyBorder(0, 1, 0, 1);
    private final Border highlight = new CompoundBorder(outside, inside);
    
    int vacios = 0;

    //tableModel  = new DefaultTableModel(0, 2);
    @Override
    public void newTable() {

        Dimension paneSize = new Dimension(1000, 550);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JDialog dialog = new JDialog();
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setPreferredSize(paneSize);
                panel.setOpaque(true);
                JTable table1 = new JTable(tableModel) {

                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
                        JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);
                        if (!((getValueAt(rowIndex, columnIndex) == null) || (getValueAt(rowIndex, columnIndex).toString().matches(" ")))) {
                            component.setBackground(new java.awt.Color(255, 255, 255));   //SI
                            
                        } else {
                            component.setBackground(new java.awt.Color(255, 196, 75));   //SI    
                        }
                        setSelectionBackground(new java.awt.Color(0, 0, 0)); //Color de FONDO SI
                        setSelectionForeground(new java.awt.Color(0, 0, 139).brighter()); //Color de la letra cuando se selecciona SI!

                        return component;
                    }
                };
                table1.changeSelection(0, 0, false, false);
                resizeColumnWidth(table1);
                JScrollPane scroller = new JScrollPane(table1);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JButton buttonDeleteEmpty = new JButton("Borrar Vacios");
                buttonDeleteEmpty.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            buttonActionPerformedDelete(evt);
                        } catch (IOException ex) {
                            Logger.getLogger(NewDialogTB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });                
                JButton buttonSaveChanges = new JButton("Guardar");
                buttonSaveChanges.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            buttonActionPerformedSave(evt);
                        } catch (IOException ex) {
                            Logger.getLogger(NewDialogTB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });                
                panel.add(scroller);
                inputpanel.add(buttonDeleteEmpty);
                inputpanel.add(buttonSaveChanges);
                panel.add(inputpanel);
                dialog.setModal(true);
                dialog.getContentPane().add(BorderLayout.CENTER, panel);
                dialog.pack();
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
                dialog.setResizable(true);
            }
        });
    }

    @Override
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    private void buttonActionPerformedDelete(java.awt.event.ActionEvent evt) throws IOException {
        FileActions.objLoadF.removeRow();
        JOptionPane.showMessageDialog(null, "Guarda tus cambios antes de salir!!");
    }   

    private void buttonActionPerformedSave(java.awt.event.ActionEvent evt) throws IOException {                                         
        //Se vuelve a cargar el modelo iterando la tabla
            IterateTable.objIterateT.iterateTable();
            JOptionPane.showMessageDialog(null, "Cambios guardados con �xito!");
    }       
}
