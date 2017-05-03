/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlmc;

import static etlmc.NewDialogTB.tableModel;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author nwni
 */
public class IterateTable {

    final static IterateTable objIterateT = new IterateTable();
    JComponent component1;
    Component comp;
    String everything;
    static ArrayList al = new ArrayList();

    //Itera toda la tabla y busca por espacios en blanco, imprimiendo en un Label cantidad de celdas en blanco para cada tabla que se seleccione
    public void iterateTable() throws IOException {
        int rows = tableModel.getRowCount();
        int columns = tableModel.getColumnCount();
        int vacios = 0;

        String encontrado, id;
        al.clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //Se extrae el Objeto de la celda i,j y se convierte a String
                encontrado = (String) tableModel.getValueAt(i, j);
                //Se le agrega la condicion (encontrado==null) para los campos null de la tabla expediente
                id = (String) tableModel.getValueAt(i, 0);
                if ((encontrado == null) || (encontrado.matches(" "))) {
                    System.out.println("Encontrado -> "+id);
                    al.add(id);//Array list con los id de las filas que tienen elementos vacios
                    vacios++;
                }
            }
        }
        //mainF.jLabel4.setText("Se encontraron: " + vacios + " campos vacios en la tabla " + FileActions.tablaF);
    }
}
