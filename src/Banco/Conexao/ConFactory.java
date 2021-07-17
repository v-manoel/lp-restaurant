/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

/**
 *
 * @author apmagalhaes
 * 
 */
public class ConFactory {

    public ConFactory(){
    
    }
    
    public Connection conectar(){
        try{
            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_restaurante","root", "123456");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_test","root", "123456");
            

        }catch (SQLException e) { 
            throw new RuntimeException(e);
        }
   }
    
    public static void closeConexao(Connection con){
        try {
            if(con != null){    //Significa que a conexao está aberto
                con.close();
            } 
        }catch (SQLException ex) {
                throw new RuntimeException(ex);
        }
    }
    
    public static void closeConexao(Connection con, PreparedStatement stmt){
        closeConexao(con);
        try {
            if(stmt != null){    //Significa que a conexão está aberto
                stmt.close();
            } 
        }catch (SQLException ex) {
                throw new RuntimeException(ex);
        }
    }
    
    public static void closeConexao(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConexao(con, stmt);
        try {
            if(rs != null){    //Significa que a conexão está aberto
                rs.close();
            } 
        }catch (SQLException ex) {
                throw new RuntimeException(ex);
        }
    }
}
