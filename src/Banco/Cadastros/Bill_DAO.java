/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco.Cadastros;

import Banco.Conexao.ConFactory;
import Negocio.Pessoas.Client;
import Negocio.Servicos.Bill;
import Negocio.Servicos.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dcet1-lami11-ubuntu
 */
public class Bill_DAO {
    
    private Connection con;
    
    public Bill_DAO(){
    }
    
    //Atualizar dados da conta - Chamada apos pagamento
    public boolean Atualizar(Bill conta){
        
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        try {
            //Passagem de parametros
            stmt = con.prepareStatement("UPDATE sql10326340.conta SET cpf = ?, data = ?, valor = ?, pagamento = ? WHERE id = ?");
            stmt.setString(1,conta.getClient().getCpf());
            stmt.setString(2,conta.getDate());
            stmt.setFloat(3,conta.getValue());
            stmt.setString(4,conta.getPayment_method());
            stmt.setInt(5,conta.getId());
            
            //Execução da SQL
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao qtualizar Conta - Bill_DAO.Atualizar - "+ex);
            throw new RuntimeException(ex);
            //Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex); --> ex, acima
        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Iniciar uma conta, para que os itens sejam inseridos conforme acoes do cliente
    public boolean Pre_Inserir(Bill conta){
        
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //Passagem de parametros
            stmt = con.prepareStatement("INSERT INTO sql10326340.conta(cpf,data,valor,pagamento)VALUES(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,conta.getClient().getCpf());
            stmt.setString(2,"NONE");
            stmt.setFloat(3,0.0f);
            stmt.setString(4,conta.getPayment_method());
            
            //Execução da SQL
            stmt.executeUpdate();
            
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                conta.setId(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Inicializar Conta - Bill_DAO.Pre_Inserir - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       }
        return true;
    }
    
    //Atualizar o campo pagamento na tabela BD
    public boolean Atualizar_Pagamento(Bill conta){
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        
        try {
            //Passagem de parametros
            stmt = con.prepareStatement("UPDATE sql10326340.conta SET pagamento = ? WHERE id = ?");
            stmt.setString(1,conta.getPayment_method());
            stmt.setInt(2,conta.getId());
            
            //Execução da SQL
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Atualizar Campo Pagamento - Bill_DAO.Ataulizar_Pagamento - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Excluir uma conta e todos as suas referencias no BD
    public boolean Excluir(Bill c1){
        
        //Exclusão dos pedidos relacionados a Conta
        Order_DAO order_dao = new Order_DAO();
        List<Order> pedidos = order_dao.Carregar_pConta(c1.getId());
        for(Order pedido : pedidos){
            pedido.Excluir();
        }

        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        try {
            //Passagem de parametros
            stmt = con.prepareStatement("DELETE FROM sql10326340.conta WHERE cpf = ?");
            stmt.setString(1,c1.getClient().getCpf());
            
            //Execução da SQL
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Excluir Conta - Bill_DAO.Excluir - "+ex);
            throw new RuntimeException(ex);
            //Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex); --> ex, acima
        }finally{
           ConFactory.closeConexao(con, stmt);
       }
        return true;
    }
    
    //Buscar Cliente relacionado a conta
    public Client BuscarClient(int id_conta){
        Client c1 = new Client();
               
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT cpf FROM sql10326340.conta WHERE id = ?");   //Selecione todas as colunas da tabela produto
            stmt.setInt(1,id_conta);
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            if (rs.next()){
                Pessoa_DAO pessoa_dao = new Pessoa_DAO();
                c1 = pessoa_dao.Buscar_pCpf(rs.getString("cpf"));
            }
            
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Buscar Cliente - Bill_DAO.BuscarClient - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       }           
       
        return c1;
    }
    
    //Carregar os itens referentes aos pedidos ligados a conta
    public Bill CarregarItems(Bill conta){
        //Carregando pedidos para conta
        Order_DAO order_dao = new Order_DAO();
        List<Order> pedidos = order_dao.Carregar_pConta(conta.getId());
        
        //Carregando itens de cada pedido carregado
        for(Order pedido : pedidos){
            Order_DAO order_dao2 = new Order_DAO();
            pedido = order_dao2.CarregarItems(pedido);
        }
            conta.setOrders(pedidos);
        return conta;
    }
    
    //Carregar todas as contas do BD
    public List<Bill> CarregarContas(){
        List<Bill> contas = new ArrayList<>();
                
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM sql10326340.conta");   //Selecione todas as colunas da tabela produto
            rs = stmt.executeQuery(); //Metodo responsavel por consultas ao banco
            
            while (rs.next()){
                Bill conta = new Bill();
                Client c1 = new Client();
                c1.setCpf(rs.getString("cpf"));
                conta.setClient(c1);
                conta.setDate(rs.getString("data"));
                conta.setValue(rs.getFloat("valor"));
                conta.setPayment_method(rs.getString("pagamento"));

                contas.add(conta);
            }
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carregar Contas - Bill_DAO.CrregarContas - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
 
        return contas;
    }
    
    //Carregar todas as contas fechadas ou nao de um determinado cliente
    public List<Bill> CarregarContas_pCPF(Client c1){
        List<Bill> contas = new ArrayList<>();
        
        this.con = new ConFactory().conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM sql10326340.conta WHERE cpf = ?");
            stmt.setString(1,c1.getCpf());
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Bill conta = new Bill();
                conta.setClient(c1);
                conta.setId(rs.getInt("id"));
                conta.setDate(rs.getString("data"));
                conta.setValue(rs.getFloat("valor"));
                conta.setPayment_method(rs.getString("pagamento"));

                contas.add(conta);
            }
            
            
        } catch (SQLException ex) {
            System.out.println( "Erro ao Carregar Contas por CPF - Bill_DAO.CarregarContas_pCPF - "+ex);
            throw new RuntimeException(ex);
        }finally{
           ConFactory.closeConexao(con, stmt, rs);
       } 
 
        return contas;
    }
}
