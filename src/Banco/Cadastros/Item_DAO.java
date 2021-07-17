/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco.Cadastros;

import Banco.Conexao.ConFactory;
import Negocio.Pratos.Drink;
import Negocio.Pratos.Food;
import Negocio.Pratos.Menu_Item;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @authors Filipe Silva, Reinilson Bispo, Vitor Manoel.
 */
public class Item_DAO {
    private Connection con;
    
    public Item_DAO(){
    }
    
    //Inserção de Bebida na tabela Item do BD:
    public boolean Inserir(Drink drink0, short tp_item){
       con = new ConFactory().conectar();
       PreparedStatement stmt = null;
       String sql = "INSERT INTO sql10326340.item(nome, preco, fornecedor, tp_item)VALUES(UPPER(?), ?, ?, ?)"; 
       
       try {     
            stmt = con.prepareStatement(sql);

            stmt.setString(1, drink0.getName());
            stmt.setFloat(2, drink0.getPrice());        
            stmt.setString(3, drink0.getProvider());
            stmt.setShort(4, tp_item);
            
            stmt.executeUpdate(); //executa comando       
            stmt.close();
           
            
        }catch (SQLException u) {
            System.out.println( "Erro ao Inserir Bebida - Items_DAO.Inserir-"+u);
            throw new RuntimeException(u);        
        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Inserção de Bebida na tabela Item do BD:    
    public boolean Inserir(Food food0, short tp_item){
       con = new ConFactory().conectar();
       PreparedStatement stmt = null;
       String sql = "INSERT INTO sql10326340.item(nome, preco, descricao, tp_item)VALUES(UPPER(?), ?, ?, ?)"; 
       
       try {     
             stmt = con.prepareStatement(sql);

            stmt.setString(1, food0.getName());
            stmt.setFloat(2, food0.getPrice());        
            stmt.setString(3, food0.getSteps());
            stmt.setShort(4, tp_item);
            
            stmt.executeUpdate(); //executa comando       

        }catch (SQLException u) { 
            System.out.println( "Erro ao Inserir Comida - Items_DAO.Inserir-"+u);
            throw new RuntimeException(u);        
        }finally{
           ConFactory.closeConexao(con, stmt);
       } 
       return true;
    }
    
    
    //Carregar todas Bebidas cadastradas no BD
    public List<Drink> CarregarDados_Bebida(){
        List<Drink> bebidas = new ArrayList<>();

        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_item, nome, preco FROM sql10326340.item WHERE tp_item = 1";
        
        try {
                stmt = con.prepareStatement(sql);
                
                rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
                
                while (rs.next()){
                    Drink bebida = new Drink();
                    bebida.setId(rs.getInt("id_item"));
                    bebida.setName(rs.getString("nome"));
                    bebida.setPrice(rs.getFloat("preco"));
                
                    bebidas.add(bebida);
                }
  
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carregar Items Bebida - Items_DAO.CarregarDados_Bebida-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        
        
        return bebidas;
    }
    
    //Carregar todas Comidas cadastradas no BD
    public List<Food> CarregarDados_Comida(){
        List<Food> comidas = new ArrayList<>();
        
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id_item, nome, descricao, preco FROM sql10326340.item WHERE tp_item = 2";
        
        try {
            
            stmt = con.prepareStatement(sql);
                
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
                
            while (rs.next()){
                Food comida = new Food();
                comida.setId(rs.getInt("id_item"));
                comida.setName(rs.getString("nome"));
                comida.setSteps(rs.getString("descricao"));
                comida.setPrice(rs.getFloat("preco"));
                
                comidas.add(comida);
            }
            

        } catch (SQLException ex) {
            System.out.println( "Erro ao Carregar Items Comida - Items_DAO.CarregarDados_Comida-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
           
        return comidas;
    }
    
    //Retorna um item (comida ou bebida) especifico a partir do seu Id
    public Menu_Item CarregarDados_Item(int id){
        Menu_Item item = new Menu_Item();
        
        con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT nome, preco FROM sql10326340.item WHERE id_item = ?");  
            stmt.setInt(1,id);
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            if(rs.next()){
                item.setId(id);
                item.setName(rs.getString("nome"));
                item.setPrice(rs.getFloat("preco"));
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carregar Items - Items_DAO.CarregarDados_Items-"+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
        return item;
    }
}
