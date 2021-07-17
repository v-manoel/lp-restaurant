package Negocio.Pratos;

import Banco.Cadastros.Item_DAO;
import java.util.List;

public class Drink extends Menu_Item{
    private String provider;
    
    public String getProvider() {
        return provider;
    }

    public void setProvider(String fornecedor) {
        this.provider = fornecedor;
    }

    public boolean Inserir(){
        Item_DAO item_dao = new Item_DAO();
        short tp_item = 1;
        return item_dao.Inserir(this, tp_item);
    }
    
    public List<Drink> CarregarDados(){
        Item_DAO item_dao = new Item_DAO();
        return item_dao.CarregarDados_Bebida();
    }
    
    @Override
        public void PrintItem(){
        super.PrintItem();
        System.out.println(provider);
    }
}
