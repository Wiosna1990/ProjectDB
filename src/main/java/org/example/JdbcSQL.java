package org.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcSQL {
    public static void main(String[] args) {

        Connection conn = null;

        try {

            String dbURL = "jdbc:sqlserver://LAPTOP-59PGLUF4\\sqlexpress;databaseName=EmergencyMedicalService;encrypt=false";
            String user = "Wiosna";
            String pass = "P3LiK4N";
            System.out.println("Próba połączenia z bazą danych...");
            conn = DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Połączenie z bazą danych nawiązane!");
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("SQLException: " + ex.getMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println("SQLException: " + ex.getMessage());
            }
        }


    }


}