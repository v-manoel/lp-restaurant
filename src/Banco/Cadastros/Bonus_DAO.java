/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco.Cadastros;

import Banco.Conexao.ConFactory;
import Negocio.Pessoas.Client;
import Negocio.Servicos.Bonus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marizane
 */
public class Bonus_DAO {
    private Connection con;
    
    public Bonus_DAO(){

    }
    
    //Inserir Bonus de cliente no BD
    public boolean Inserir(Bonus bonus, Client c1){
        this.con = new ConFactory().conectar();        
        PreparedStatement stmt = null;
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement("INSERT INTO sql10326340.bonus(cpf_cliente,valor,data,situacao)VALUES(?,?,?,?)");
            stmt.setString(1,c1.getCpf());
            stmt.setFloat(2,bonus.getValue());
            stmt.setString(3,bonus.getDate());
            stmt.setInt(4,bonus.getSituacao());
            
            //Execução da SQL
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Inserir Bonus - Bonus_DAO.Inserir -"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       } 
        return true;
    }
    
    //Setar bonus como utilizado (Used)
    public boolean Atualizar_Situacao(Bonus bonus){
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement("UPDATE sql10326340.bonus SET situacao = ? WHERE id = ?");
            stmt.setInt(1,1);
            stmt.setInt(2,bonus.getId());
            
            //Execução da SQL
            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            System.out.println( "Erro ao atualizar identificador de uso - Bonus_DAO.Atualizar_Situacao -"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       } 
        return true;
    }
    
    //Excluir todos os bonus ligados ao CPF de um determinado cliente
    public boolean Excluir_pCpf(Client c1){
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement("DELETE FROM sql10326340.bonus WHERE cpf_cliente = ?");
            stmt.setString(1,c1.getCpf());
            
            //Execução da SQL
            stmt.executeUpdate();
                        
        } catch (SQLException ex) {
            System.out.println( "Erro ao exluir bonus pelo cpf - Bonus_DAO.Excluir_pCpf -"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       } 
        return true;
    }
    
    //Resgatar bonus validos cliente por cpf e data
    public Bonus Buscar_pCpf(Client c1, String date){
        Bonus bonus = new Bonus();
        
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM sql10326340.bonus WHERE (cpf_cliente = ? AND data != ? AND situacao = 0)");  
            stmt.setString(1,c1.getCpf());
            stmt.setString(2,date);
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            if(rs.next()){
                bonus.setId(rs.getInt("id"));
                bonus.setValue(rs.getFloat("valor"));
                bonus.setDate(rs.getString("data"));
                bonus.setSituacao(rs.getInt("situacao"));
            }else{
                bonus.setValue(0.0f);
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao buscar bonus por cpf e data - Bonus_DAO.Buscar_pCpf-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        return bonus;
        
    }
    
    //Buscar bonus valido de um determinado Cliente
    public Bonus Buscar_pCpf(Client c1){
        Bonus bonus = new Bonus();
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        try {
            stmt = con.prepareStatement("SELECT * FROM sql10326340.bonus WHERE (cpf_cliente = ?  AND situacao = 0)");  
            stmt.setString(1,c1.getCpf());
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            if(rs.next()){
                bonus.setId(rs.getInt("id"));
                bonus.setValue(rs.getFloat("valor"));
                bonus.setDate(rs.getString("data"));
                bonus.setSituacao(rs.getInt("situacao"));
            }else{
                bonus.setValue(0.0f);
                return bonus;
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao buscar bonus por cpf - Bonus_DAO.Buscar_pCpf-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        return bonus;
        
    }
    
    //Resgatar todas relacoes bonus - cliente
    public List<Client> CarregarBonus(){
        List<Client> clientes = new ArrayList<>();
        
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        try {
            stmt = con.prepareStatement("SELECT * FROM sql10326340.bonus");   //Selecione todas as colunas da tabela produto
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            while (rs.next()){
                Client c1 = new Client();
                Bonus b1 = new Bonus();
                c1.setCpf(rs.getString("cpf_cliente"));
                b1.setValue(rs.getFloat("valor"));
                b1.setDate(rs.getString("data"));
                b1.setSituacao(rs.getInt("situacao"));
                c1.setBonus(b1);
                clientes.add(c1);
            }
             
        } catch (SQLException ex) {
            System.out.println( "Erro ao carregar Bonus - Bonus_DAO.CarregarBonus-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
 
        return clientes;
    }
    
    //Carregar todos os bonus ligados a um determinado cliente
    public List<Bonus> CarregarBonus_pCPF(Client c1){
        List<Bonus> bonus = new ArrayList<>();
        
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        try {
            stmt = con.prepareStatement("SELECT * FROM sql10326340.bonus WHERE cpf_cliente = ?");   //Selecione todas as colunas da tabela produto
            stmt.setString(1,c1.getCpf());
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            while (rs.next()){
                Bonus b1 = new Bonus();
                b1.setValue(rs.getFloat("valor"));
                b1.setDate(rs.getString("data"));
                b1.setSituacao(rs.getInt("situacao"));
                bonus.add(b1);
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao carregar Bonus por cpf - Bonus_DAO.CarregarBonus_pCPF-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
 
        return bonus;
    }
}

