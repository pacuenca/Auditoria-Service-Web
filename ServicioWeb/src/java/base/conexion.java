/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Erika
 */
public class conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/servicioprueba";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String SENHA = USER;
    
    private static Connection con;
    public static Connection cb() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL,USER,SENHA);
        return con;
    }
    
    public static void  db() throws SQLException{
        con.close();
    }
}
