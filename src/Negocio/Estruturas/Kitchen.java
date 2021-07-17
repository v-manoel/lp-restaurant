
package Negocio.Estruturas;

import Banco.Cadastros.Bill_DAO;
import Banco.Cadastros.Order_DAO;
import Negocio.Pessoas.Client;
import Negocio.Servicos.Order;
import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private List<Order> comandas;

    public Kitchen() {
        Order_DAO order_dao = new Order_DAO();
        comandas = order_dao.Carregar();
    }  
        
    public boolean CloseComandas(){
            Order order;
        for (Order comanda : comandas) {
            order = comanda;
            if((order.getStatus()).equals("Opened")){
                ///fecgar
            }
        }
            return true;
    }
    public Client ClientePedido(int id_conta){
        Bill_DAO bill_dao = new Bill_DAO();
        return bill_dao.BuscarClient(id_conta);
    }
        
    public Order InfoPedido(Order pedido){
        Order_DAO order_dao = new Order_DAO();
        return  order_dao.CarregarItems(pedido);
    }
    
    public List<Order> ComandasAbertas(String status){
        Order_DAO order_dao = new Order_DAO();
        return order_dao.Carregar(status);  
    }
    
    public List<Order> PedidosAbertos(String status){
        List<Order> pedidos_abertos = new ArrayList<>();
        for(Order pedido : comandas){
            if(pedido.getStatus().equals(status))
                pedidos_abertos.add(pedido);
        }
        return pedidos_abertos;
    }

    public List<Order> getComandas() {
        return comandas;
    }

    public void setComandas(List<Order> comandas) {
        this.comandas = comandas;
    }
    
    
}
    