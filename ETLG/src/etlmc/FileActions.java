/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlmc;

import static etlmc.IterateTable.al;
import static etlmc.NewDialogTB.tableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author nwni
 */
public class FileActions {

    static final FileActions objLoadF = new FileActions();
    static String tablaF, everything;
    static JFileChooser chooserS;
    File file;
    static File fileSaved;
    static boolean YA_ENTRO = false, B_CAPITALIZAR = false;

    public File getFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "SQL Files .sql", "sql");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {

        }
        return file;
    }

    public void saveFile() throws IOException {
        chooserS = new JFileChooser();
        int returnVal = chooserS.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            saveFile(chooserS.getSelectedFile());
        }
    }

    public void saveFile(File file1) throws IOException {
        BufferedWriter bfw;
        try {
            if (!file1.exists()) {
                file1.createNewFile();
            }
            bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "ISO-8859-1"));//UTF-8 ISO-8859-1
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                bfw.write(tableModel.getColumnName(i));
                bfw.write(",");
            }

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                bfw.newLine();
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bfw.write((String) (tableModel.getValueAt(i, j)));
                    bfw.write(",");
                }
            }
            bfw.close();
            JOptionPane.showMessageDialog(null, "Archivo exportado con exito!");
            } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeRow() throws FileNotFoundException, IOException {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < al.toArray().length; j++) {
                if (al.get(j) == tableModel.getValueAt(i, 0)) {
                    tableModel.removeRow(i);
                }
            }
        }
    }
//Si
    public void findModel(File file) throws FileNotFoundException, IOException {
        String line;
        BufferedReader bfr;
        String pathToFile = file.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        //bfr = new BufferedReader(new FileReader(pathToFile));
        bfr = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), "UTF-8"));
        line = bfr.readLine();
        while (line != null) {
            //Para detectar de que tabla viene
            //System.out.println("ok");
            if (line.contains("table `empleado`")) {FindModel.objFindM.foundTable(0);break;
            } else if (line.contains("table `doctor`")) {FindModel.objFindM.foundTable(1);break;
            } else if (line.contains("table `recepcionista`")) {FindModel.objFindM.foundTable(2);break;
            } else if (line.contains("table `laboratorista`")) {FindModel.objFindM.foundTable(3);break;
            } else if (line.contains("table `enfermero`")) {FindModel.objFindM.foundTable(4);break;
            } else if (line.contains("table `cuarto`")) {FindModel.objFindM.foundTable(5);break;
            } else if (line.contains("table `paciente`")) {FindModel.objFindM.foundTable(6);break;
            } else if (line.contains("table `expediente`")) {FindModel.objFindM.foundTable(7);break;
            } else if (line.contains("table `cirugia`")) {FindModel.objFindM.foundTable(8);break;
            } else if (line.contains("table `citas`")) {FindModel.objFindM.foundTable(9);break;
            } else if (line.contains("table `factura`")) {FindModel.objFindM.foundTable(10);break;
            } else if (line.contains("table `medicamentos`")) {FindModel.objFindM.foundTable(11);break;
            } else if (line.contains("table `farmacia`")) {FindModel.objFindM.foundTable(12);break;
            }
            //tableModel.addRow(line.split(","));
            sb.append(line);
            //System.out.println("LINEA!-> "+ line);
            line = bfr.readLine();

        }
        bfr.close();
    }
//Si
    public void cleanFile(File file) throws FileNotFoundException, IOException {
        String pathToFile = file.getAbsolutePath();
        //try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), "UTF-8"))) {
            //Patr�n para eliminar los comentarios que genera MYSQL
            //Pattern pattern = Pattern.compile("(^/\\*.*)|(^--.*)");
            //Regex para remover comentarios generados por MYSQL dump, tambien se remueven los espacios en blanco
            String patternComments = "(^/\\*.*)|(^--.*)|(\\))|(\\()|(INSERT INTO)|(VALUES)|('+)|" + tablaF;
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                //line = line.replaceAll(insert, "").replaceAll(rparen, "").replaceAll(patternComments, "");
                line = line.replaceAll(patternComments, "");
                line = line.replaceAll(("^\\s+"), "");   //Se eliminan los espacios que estan al inicio de cada l�nea
                line = line.replaceAll((","), ", "); //Se sustituyen las ',' por ', ' //**Esto sirve para el m�todo de capitalizar! y para estetica del doc
                sb.append(line);
                line = br.readLine();
            }
            br.close();
            everything = sb.toString().replaceAll(";", "\n");
            toFile(everything);
        }
    }

    //??? 
    public void reDo(File file) throws FileNotFoundException, IOException {
        String pathToFile = file.getAbsolutePath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            br.close();
            everything = sb.toString().replaceAll(";", "\n");
            //toFile(everything);

        }
    }
//No
    public void toFile(String ev) throws IOException {
        BufferedWriter bfw;
        File justData = new File("tmp.csv");
        if (!justData.exists()) {
            justData.createNewFile();
        }
        bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(justData), "UTF-8"));//UTF-8 ISO-8859-1
        bfw.write(ev);
        bfw.close();
    }
//No...
    public void toTable() throws FileNotFoundException, IOException {
        String line;
        BufferedReader bfr;
        //bfr = new BufferedReader(new FileReader("tmp.csv"));
        bfr = new BufferedReader(new InputStreamReader(new FileInputStream("tmp.csv"), "UTF-8"));//ISO-8859-1
        tableModel.setRowCount(0); //Se resetean las filas de la tabla para que no se acumulen
        while ((line = bfr.readLine()) != null) {
            tableModel.addRow(line.split(","));
        }
        bfr.close();
    }
    
//Still working on it....
    public void capitalizarDatos(File file) throws FileNotFoundException, IOException {
        String line, wholefile;
        BufferedReader bfr;
        StringBuilder sb = new StringBuilder();
        //bfr = new BufferedReader(new InputStreamReader(new FileInputStream("tmp.csv"), "UTF-8"));
        bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        line = bfr.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = bfr.readLine();
        }
        bfr.close();
        wholefile = WordUtils.capitalizeFully(sb.toString()).replaceAll("^\\s", "");
        toFile(wholefile);
    }
    
//Si
    public void exportFile() throws IOException {
        File fexported = null;
        JFileChooser export = new JFileChooser();
        int returnVal = export.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fexported = export.getSelectedFile();
            exportToSQL(fexported);
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            //Cancelado por el usuario.
        }
    }

    //Si
    public void exportToSQL(File file) throws IOException {
        String columnas = "(" + FindModel.objFindM.columnas + ")";
        int stringValues[] = FindModel.objFindM.stringValues;
        String insert_final, tabla;
        BufferedWriter bfw;
        tabla = FileActions.tablaF.replaceAll("(\\()|(\\))", "");
        if (!file.exists()) {
            file.createNewFile();
        }
        bfw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));//UTF-8 ISO-8859-1
        int x = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            bfw.newLine();
            String insert = "INSERT INTO " + tabla + columnas + "VALUES (";
            String insert2;
            for (int j = 0; j < tableModel.getColumnCount(); j++) {

                if (j == tableModel.getColumnCount()) {//SI ES EL ULTIMO
                    insert2 = tableModel.getValueAt(i, j) + "";
                    insert = insert + insert2;
                }
                if (stringValues[j] == 1) { //Si se tiene prendida su bandera en la lista, se formatea de otra manera
                    insert2 = "'" + tableModel.getValueAt(i, j) + "',";
                    insert = insert + insert2;
                } else {
                    insert2 = tableModel.getValueAt(i, j) + ",";
                    insert = insert + insert2;
                }
            }
            insert_final = insert + ");";
            bfw.write(insert_final.replaceAll(",\\);.*", ");")); //Cuando detecta una coma al final de la linea la sustituye
        }
        bfw.close();
        JOptionPane.showMessageDialog(null, "Archivo exportado con exito!");
    }
}
