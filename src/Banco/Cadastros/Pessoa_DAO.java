/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco.Cadastros;

import Banco.Conexao.ConFactory;
import Negocio.Pessoas.Client;
import Negocio.Servicos.Bill;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author qwerty
 */
public class Pessoa_DAO {
    private Connection con;
    
    //construtor
    public Pessoa_DAO(){
    }
    
    //Inserir novo cliente no BD
    public boolean Inserir(Client c0){
       con = new ConFactory().conectar();
       PreparedStatement stmt = null;
       String sql = "INSERT INTO sql10326340.cliente(cpf, nome, email, senha)VALUES(?, ?, ?, MD5(?))"; 
       
       try {     
            stmt = con.prepareStatement(sql);

            stmt.setString(1, c0.getCpf());
            stmt.setString(2, c0.getName());        
            stmt.setString(3, c0.getEmail());
            stmt.setString(4, c0.getPswd());
            
            stmt.executeUpdate(); //executa comando       
            stmt.close();
            
        }catch (SQLException ex) {
            System.out.println( "Erro ao Inserir cliente - Pessoa_DAO.Inserir - "+ex);
            throw new RuntimeException(ex);        
        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Busacr cliente atraves do CPF
    public Client Buscar_pCpf(String cpf){
        Client c1 = new Client();
        boolean check = false;
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        try {
            stmt = con.prepareStatement("SELECT * FROM sql10326340.cliente WHERE cpf = ?");  
            stmt.setString(1,cpf);
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            if(rs.next()){
                check = true;
                c1.setCpf(rs.getString("cpf"));
                c1.setName(rs.getString("nome"));
                c1.setEmail(rs.getString("email"));
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Buscar cliente por CPF- Pessoa_DAO.Buscar_pCpf - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt,rs);
       }
        if(check)
            return c1;
        else
            return null;
    }
    
    //Carregar dados de determinado cliente
    @SuppressWarnings("empty-statement")
    public List<Client> CarregarDados(Client c1){
        List<Client> clientes = new ArrayList<>();
        String sql;
        String campo;
        
        if(c1.getCpf().equals("")){
            sql = "SELECT cpf, nome, email FROM sql10326340.cliente WHERE nome LIKE ?";
            campo = c1.getName();
        }
        else{
            sql = "SELECT cpf, nome, email FROM sql10326340.cliente WHERE cpf LIKE ?";
            campo = c1.getCpf();
        }
        
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + campo + "%");
                
            rs = stmt.executeQuery(stmt.toString().replaceAll("com.mysql.cj.jdbc.ClientPreparedStatement: ", "")); //Metodo responsavel por consultas ao banco
                
            while (rs.next()){
                Client cliente = new Client();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setName(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                
                clientes.add(cliente);
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carreagr todos os clientes - Pessoa_DAO.CarregarDados - "+ex);
            throw new RuntimeException(ex);   
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       }
        
        return clientes;
    }
    
    //Atualizar dados do cliente
    public boolean Atualizar(Client c1){
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        String sql = "UPDATE sql10326340.cliente SET nome = ?,email = ? WHERE cpf = ?";
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement(sql);
            stmt.setString(1,c1.getName());
            stmt.setString(2,c1.getEmail());
            stmt.setString(3, c1.getCpf());
            
            //Execução da SQL
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Atualizar cliente - Pessoa_DAO.Atualizar - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Excluir cliente do BD e todas suas dependencias (contas,bonus)
    public boolean Excluir(Client c1){
        //Excluir bonus relacionado ao cliente
        Bonus_DAO bonus_dao = new Bonus_DAO();
        bonus_dao.Excluir_pCpf(c1);
        
        //Excluir conta relacionado ao cliente
        Bill_DAO bill_dao = new Bill_DAO();
        List<Bill> contas = bill_dao.CarregarContas_pCPF(c1);
        for(Bill conta : contas){
            conta.Excluir();
        }
        
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM sql10326340.cliente WHERE cpf = ?";

        try {
            //Passagem de parametros
            stmt = con.prepareStatement(sql);
            stmt.setString(1,c1.getCpf());
            
            //Execução da SQL
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Excluir cliente - Pessoa_DAO.Excluir - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Alterar senha cliente
    public boolean Alterar_Senha(Client c1){
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        String sql = "UPDATE sql10326340.cliente SET senha = MD5(?) WHERE cpf = ?";
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement(sql);
            stmt.setString(1,c1.getPswd());
            stmt.setString(2, c1.getCpf());
            
            //Execução da SQL
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Alterar senha cliente - Pessoa_DAO.Alterar_Senha - "+ex);
            throw new RuntimeException(ex);

        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Realizar login do cliente
    public Client Login(Client c1){
        boolean check = false;
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sql10326340.cliente WHERE cpf = ? and senha = MD5(?)";
        
        try {
            stmt = con.prepareStatement(sql);  
            stmt.setString(1,c1.getCpf());
            stmt.setString(2,c1.getPswd());
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            if(rs.next()){
                check = true;
                c1.setName(rs.getString("nome"));
            }
           
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Logar cliente - Pessoa_DAO.Login -"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       }
        
        if(check)
            return c1;
        else
            return null;
    }
}

