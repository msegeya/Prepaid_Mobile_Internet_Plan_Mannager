package com.devspace.MIPM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
	 public static int setData(Connection connection, String sql) throws SQLException{

         Statement stm = connection.createStatement();
         int result = stm.executeUpdate(sql);
         return result;
 }


 public static int setData(Connection connection, String sql, 
         Object data[]) throws SQLException{

         PreparedStatement pstm = connection.prepareStatement(sql);

         for(int i = 0; i < data.length; i++){
                 pstm.setObject((i+1), data[i]);
         }
         return pstm.executeUpdate();

 }



 public static ResultSet getData(Connection connection, String sql)throws SQLException{

         Statement stm = connection.createStatement();
         ResultSet result = stm.executeQuery(sql);
         return result;
 }
}
