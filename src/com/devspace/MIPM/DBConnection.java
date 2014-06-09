package com.devspace.MIPM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBConnection {
	private static DBConnection dbConnection;
	
    private Connection connection;

    private DBConnection(){
     try {
         try {
             Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
             //JOptionPane.showMessageDialog(null,"Connection Error");
             Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         connection = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/mipm","root","");
         if(connection==null){
             //JOptionPane.showMessageDialog(null,"Connection Error");
         }
     } catch (SQLException ex) {
         //JOptionPane.showMessageDialog(null,"Connection Error");
         Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
     }

    }

    public  Connection getConnection(){
                    return connection;
    }

    public static DBConnection getDbConnection()throws ClassNotFoundException, SQLException{
                    if(dbConnection == null){
                                    dbConnection = new DBConnection();
                    }
                    return dbConnection;
    }

    public static Connection getConnectionToDB() throws ClassNotFoundException,
     SQLException{

                    DBConnection dbCon = DBConnection.getDbConnection();

                    return dbCon.getConnection();
    }

}
