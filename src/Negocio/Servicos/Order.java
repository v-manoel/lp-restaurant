package Negocio.Servicos;

import Banco.Cadastros.Order_DAO;
import java.util.ArrayList;

public class Order {
    private int id;
    ArrayList<Order_Item> items;
    private String status = "Opened";
    private float value;
    private int id_conta;

    public Order() {
        items = new ArrayList<Order_Item>();
    }
    
    public void AddItem(Order_Item item){
        items.add(item);
    }
    
    public void RemoveItem(int index){
        items.remove(index);
    }
    
    public ArrayList<Order_Item> ItensPedido(){
        ArrayList<Order_Item> produtos = new ArrayList<>();
        for(Order_Item item : items){
            produtos.add(item);
        }
        return produtos;
    }
    
    public float CalcValue(){
        Order_Item item;
        value = 0.0f;
        for(int index = 0; index < items.size(); index++){
            item = (items.get(index));
            value += item.getPrice();
        }
        return value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public boolean Inserir(){
        Order_DAO order_dao = new Order_DAO();
        return order_dao.Inserir(this);
    }
    
    public boolean Excluir(){
        Order_DAO order_dao = new Order_DAO();
        return order_dao.Excluir(this);
    }
    
    public boolean Atualizar(){
        Order_DAO order_dao = new Order_DAO();
        return order_dao.Atualizar(this);
    }
    
}
