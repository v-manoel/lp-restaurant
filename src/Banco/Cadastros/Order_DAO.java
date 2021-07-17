/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco.Cadastros;

import Banco.Conexao.ConFactory;
import Negocio.Pratos.Menu_Item;
import Negocio.Servicos.Order;
import Negocio.Servicos.Order_Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vitor M
 */
public class Order_DAO {
    private Connection con;
    
    public Order_DAO(){
    }
    
    //Inserir novo pedido realizado
    public boolean Inserir(Order pedido){
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO sql10326340.pedido(id_conta,valor,status)VALUES(?,?,?)";
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,pedido.getId_conta());
            stmt.setFloat(2,pedido.CalcValue());
            stmt.setString(3,pedido.getStatus());
            
            //Execução da SQL
            stmt.executeUpdate();
            
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                pedido.setId(rs.getInt(1));
            }
            
            ConFactory.closeConexao(con, stmt, rs);
            this.InserirItems(pedido);
        } catch (SQLException ex) {
            System.out.println( "Erro ao Inserir Pedido - Order_DAO.Inserir-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        return true;
    }
    
    //Excluir um pedido e todas suas dependências - (itemspedido)
    public boolean Excluir(Order pedido){
        this.ExcluirItems(pedido);
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM sql10326340.pedido WHERE id = ?";
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,pedido.getId());
            
            //Execução da SQL
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
             System.out.println( "Erro ao Excluir Pedido - Order_DAO.Excluir-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       } 
        return true;
    }
    
    //Excluir itens relacionados a um determinado pedido
    public boolean ExcluirItems(Order pedido){

        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM sql10326340.itemspedido WHERE id_pedido = ?";
        try {
            //Passagem de parametros
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,pedido.getId());

            //Execução da SQL
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Excluir Items- Order_DAO.ExcluirItems-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       } 
        
        return true;
    }
    
    //Carregar todos os pedidos do BD
    public List<Order> Carregar(){
        List<Order> pedidos = new ArrayList<>();
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sql10326340.pedido";

        
        try {
            stmt = con.prepareStatement(sql);   //Selecione todas as colunas da tabela produto
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            while (rs.next()){
                Order pedido = new Order();
                pedido.setId(rs.getInt("id"));
                pedido.setId_conta(rs.getInt("id_conta"));
                pedido.setValue(rs.getFloat("valor"));
                pedido.setStatus(rs.getString("status"));
                pedidos.add(pedido);
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carregar pedidos - Order_DAO.Carregar-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        return pedidos;        
    }
    
    //Atualizar status do pedido - Opened or Closed
    public boolean Atualizar(Order pedido){
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        String sql = "UPDATE sql10326340.pedido SET status = ? WHERE id = ?";
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement(sql);
            stmt.setString(1,pedido.getStatus());
            stmt.setInt(2,pedido.getId());
            
            //Execução da SQL
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Atualizar Pedido - Order_DAO.Atualizar-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       } 
        return true;
    }
    
    //Carregar pedidos por status (Opened or Closed)
    public List<Order> Carregar(String status){
        List<Order> pedidos = new ArrayList<>();
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sql10326340.pedido WHERE status LIKE ?";
   
        
        try {
            stmt = con.prepareStatement(sql);  
            stmt.setString(1,status);
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            while (rs.next()){
                Order pedido = new Order();
                pedido.setId(rs.getInt("id"));
                pedido.setId_conta(rs.getInt("id_conta"));
                pedido.setValue(rs.getFloat("valor"));
                pedido.setStatus(rs.getString("status"));
                pedidos.add(pedido);
            }
            
        } catch (SQLException ex) {
              System.out.println( "Erro ao Carregar por status - Order_DAO.Carregar-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        return pedidos;
    }
    
    //Carregar todas os pedidos relacionados a um determinada conta
     public List<Order> Carregar_pConta(int conta_id){
        List<Order> pedidos = new ArrayList<>();
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sql10326340.pedido WHERE id_conta = ?";

        
        try {
            stmt = con.prepareStatement(sql);  
            stmt.setInt(1,conta_id);
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            while (rs.next()){
                Order pedido = new Order();
                pedido.setId(rs.getInt("id"));
                pedido.setId_conta(rs.getInt("id_conta"));
                pedido.setValue(rs.getFloat("valor"));
                pedido.setStatus(rs.getString("status"));
                pedidos.add(pedido);
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carregar por conta associada - Order_DAO.Carregar_pConta -"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        return pedidos;
    }   
 
    //Inserir items em um determinado pedido
    private boolean InserirItems(Order pedido){
        List<Order_Item> items = pedido.ItensPedido();

        
        for(Order_Item item: items){
            con = new ConFactory().conectar();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO sql10326340.itemspedido(id_pedido,id_item,qntd)VALUES(?,?,?)";
            try {
               //Passagem de parametros
                stmt = con.prepareStatement(sql);
                stmt.setInt(1,pedido.getId());
                stmt.setFloat(2,item.getItem().getId());
                stmt.setInt(3,item.getQuantity());
                
            
                //Execução da SQL
                stmt.executeUpdate();
            
            } catch (SQLException ex) {
                System.out.println( "Erro ao Inserir Items no Pedido - Order_DAO.InserirItems-"+ex);
                throw new RuntimeException(ex);
            }finally{
                ConFactory.closeConexao(con, stmt);
            } 
        }
        return true;
    }
    
    //Carregar itens de um determinado pedido
    public Order CarregarItems(Order pedido){
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM sql10326340.itemspedido WHERE id_pedido LIKE ?";
        
        try {
            stmt = con.prepareStatement(sql);  
            stmt.setInt(1,pedido.getId());
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            while (rs.next()){
                Item_DAO item_dao = new Item_DAO();
                Menu_Item item_menu = item_dao.CarregarDados_Item(rs.getInt("id_item"));
                Order_Item item = new Order_Item(item_menu, rs.getInt("qntd"));
                pedido.AddItem(item);
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carreagar Items - Order_DAO.CarregarItems-"+ex);
            throw new RuntimeException(ex);
        }finally{
                ConFactory.closeConexao(con, stmt, rs);
        } 
        return pedido;
    }
}
