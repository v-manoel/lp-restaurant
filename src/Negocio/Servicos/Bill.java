
package Negocio.Servicos;

import Banco.Cadastros.Bill_DAO;
import Negocio.Pessoas.Client;
import java.util.ArrayList;
import java.util.List;

public class Bill{
    private Client client;
    private String date;
    private float value = 0.0f;
    private String payment_method = "None";
    private List<Order> orders = new ArrayList<>();
    private int id;
    
    //Conta para insercao em BD - Ao ser instanciada prepara uma conta com campos nulos para ser atualizada conforme
    //acoes do usuario
    public Bill(Client client) {
        this.client = client;
        Bill_DAO bill_dao = new Bill_DAO();
        bill_dao.Pre_Inserir(this);
    }
    
    //Conta referencia para consultas no BD
    public Bill(){
        
    }
    
    public float CalcBill(){
        for(Order order : orders)
            value += order.getValue();
        if(CheckBonus()){
            value -= (client.getBonus()).getValue();
        }
        if(value < 0)
            value = 0;
        return value;
    }
    
    public void AddItem(Order pedido){
        orders.add(pedido);
    }
       
    public boolean CloseBill(String payment_method){
        System.out.println(CalcBill());
        this.payment_method = payment_method;
        CalcBonus();
        return true;
    }
    
    private boolean CheckBonus(){
        Bonus bonus = client.getBonus();
        return !(date.equals(bonus.getDate()));
    }
    
    public float CalcBonus(){
        float client_bonus = 0f;
        client_bonus = value*0.10f;
        if((client.getBonus()).getValue() > value)
            client_bonus += ((client.getBonus()).getValue() - value);
        Bonus new_bonus = new Bonus(client_bonus, this.getDate());
        client.setBonus(new_bonus);

        return client_bonus;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public boolean Set_ClienteDB(){
        Bill_DAO bill_dao = new Bill_DAO();
        this.client = bill_dao.BuscarClient(id);
        return true;
    }
    
    public boolean CarregarItems(){
        Bill_DAO bill_dao = new Bill_DAO();
        bill_dao.CarregarItems(this);
        return true;
    }
    
    public boolean Atualizar(){
        Bill_DAO bill_dao = new Bill_DAO();
        return bill_dao.Atualizar(this);
    }
    
    public boolean Excluir(){
        Bill_DAO bill_dao = new Bill_DAO();
        return bill_dao.Excluir(this);
    }
}

