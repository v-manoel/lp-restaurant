package Negocio.Pratos;

import Banco.Cadastros.Item_DAO;
import java.util.ArrayList;
import java.util.List;

public class Food extends Menu_Item {
    private String steps;

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
    
    public boolean Inserir(){
        Item_DAO item_dao = new Item_DAO();
        short tp_item = 2;
        return item_dao.Inserir(this, tp_item);
    }
    
    public List<Food> CarregarDados(){
        Item_DAO item_dao = new Item_DAO();
        return item_dao.CarregarDados_Comida();
    }
    
    @Override
    public void PrintItem(){
        super.PrintItem();
        System.out.println(steps);
    }
}
    
   