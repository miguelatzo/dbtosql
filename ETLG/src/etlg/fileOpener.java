/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etlg;

/**
 *
 * @author mike
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class fileOpener {
    
    private static File dbFile;
    private static JFileChooser fileChooser;
    
    public fileOpener(){
        fileOpener.fileChooser = new JFileChooser();
        fileOpener.fileChooser.setFileFilter(new FileNameExtensionFilter("MS Office Documents", "accdb", "mdb"));
    }
    public void setDBFile(){
        int fileChooserResult = fileOpener.fileChooser.showOpenDialog(null);//el resultado de abrir el cuadro de dialogo para abrir archivos
        if(fileChooserResult == JFileChooser.APPROVE_OPTION){//si es exitosa
            fileOpener.dbFile = fileChooser.getSelectedFile();   
        }
    }
    
    public static File getDBFile(){
        return fileOpener.dbFile;
    }
    private static File archivo; //el archivo utilizado para escribir sobre él o para cargar un archivo existente sobre él
    private static BufferedWriter bw;//el buffer para la escritura de lineas
    private static int resultado;//integer para verificar si el archivo se abrió o guardó con éxito
    //cadena guarda las líneas leídas, línea guarda la linea que el filereader regresa, archivoreciente almacena el nombre del útimo archivo compilado
    private static String cadena = "";
    public static void guardarArchivo(String sqlStr){
        fileOpener.cadena = JOptionPane.showInputDialog("nombre del archivo");//se pregunta por el nombre del archivo
        fileOpener.archivo = new File(fileOpener.cadena+".sql");//se crea un nuevo archivo con extensión .txt y se almacena en archivo
        fileOpener.fileChooser.setSelectedFile(fileOpener.archivo);//se hace highlight/selecciona sobre el archivo para su posterior escritura 
        
        fileOpener.resultado = fileOpener.fileChooser.showSaveDialog(null);//el resultado indica si fue exitosa la operación
        if(fileOpener.resultado == JFileChooser.APPROVE_OPTION){//si fue exitosa entonces...
            try {
                fileOpener.bw = new BufferedWriter(new FileWriter(fileOpener.fileChooser.getSelectedFile()));//se inicia el buffer de escritura para el archivo creado
                fileOpener.bw.write(sqlStr); //Se escribe el contenido de Area1 en el archivo
                fileOpener.bw.close();//se desecha el buffer de escritura
                JOptionPane.showMessageDialog(null, "El archivo se ha guardado","File Saved",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(fileOpener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
