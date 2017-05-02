/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etlg;

/**
 *
 * @author mike
 */
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class fileOpener {
    
    private static File dbFile;
    private JFileChooser fileChooser;
    
    public fileOpener(){
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(new FileNameExtensionFilter("MS Office Documents", "accdb", "mdb"));
    }
    public void setDBFile(){
        int fileChooserResult = this.fileChooser.showOpenDialog(null);//el resultado de abrir el cuadro de dialogo para abrir archivos
        if(fileChooserResult == JFileChooser.APPROVE_OPTION){//si es exitosa
            fileOpener.dbFile = fileChooser.getSelectedFile();   
        }
    }
    
    public static File getDBFile(){
        return fileOpener.dbFile;
    }
    
}
