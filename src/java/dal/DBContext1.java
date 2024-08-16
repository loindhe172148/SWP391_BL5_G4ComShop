package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public abstract class DBContext1<T> {

    protected Connection connection;

    public DBContext1() {
        try {
            String user = "linh";
            String pass = "123456";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=G4COMShop;encrypt=true;trustServerCertificate=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}