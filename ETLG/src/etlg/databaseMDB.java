/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etlg;

/**
 *
 * @author mike
 */
import com.healthmarketscience.jackcess.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class databaseMDB {
    
    private static Database mdbDatabase;
    private Table sysObjectTable;
    private Table genericTable;
    public databaseMDB(){
        try{
            databaseMDB.mdbDatabase = DatabaseBuilder.open(fileOpener.getDBFile());
            
        }catch (Exception ex){
            System.out.println("corrupted file");
        }
        setSysTable();
    }
    
    private void setSysTable(){
        try{
            this.sysObjectTable = mdbDatabase.getSystemTable("MSysObjects");
        }catch (Exception e){
            System.out.println("did not find table");
        }
    }
    public static Database getMDB(){
        return databaseMDB.mdbDatabase;
    }
    
    public Table getMSysTable(){
        return this.sysObjectTable;
    }
    
    public void setGenericTable(String tableName){
        try {
            this.genericTable = databaseMDB.getMDB().getTable(tableName);
                        
        } catch (IOException ex) {
            Logger.getLogger(databaseMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Table getGenericTable(){
        return this.genericTable;
    }
}
