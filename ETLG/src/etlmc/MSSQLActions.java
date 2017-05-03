/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlmc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author nwni
 */

//Still working on it..
public class MSSQLActions {
    static MSSQLActions objquery = new MSSQLActions();

    public void executeSqlScript(Connection conn, File inputFile) {
        // Delimiter
        String delimiter = ";";
        // Create scanner
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile).useDelimiter(delimiter);
        } catch (FileNotFoundException e1) {
            return;
        }
        // Loop through the SQL file statements 
        Statement currentStatement = null;
        while (scanner.hasNext()) {
            // Get statement 
            String rawStatement = scanner.next() + delimiter;
            try {
                // Execute statement
                currentStatement = conn.createStatement();
                currentStatement.execute(rawStatement);
            } catch (SQLException e) {
            } finally {
                // Release resources
                if (currentStatement != null) {
                    try {
                        currentStatement.close();
                    } catch (SQLException e) {
                    }
                }
                currentStatement = null;
            }
        }
        scanner.close();
    }    
}
