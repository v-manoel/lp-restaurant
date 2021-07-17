package Negocio.Servicos;

import Banco.Cadastros.Bonus_DAO;
import Negocio.Pessoas.Client;
import java.util.List;


public class Bonus {
//Atributos da classe
    private int id;
    private float value;
    private String date;
    private int situacao;

    public Bonus() {
    }

    public Bonus(float value, String date) {
        this.value = value;
        this.date = date;
    }



//Métodos da classe
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSituacao() {
        return situacao;
    }
    
    public String getStrSituacao() {
        if(situacao == 0){
            return "Unused";
        }
        return "Used";
    }
    
    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public List<Client> getAllBonus(){
        Bonus_DAO bonus_dao = new Bonus_DAO();
        return bonus_dao.CarregarBonus();
    }
    
    public List<Bonus> getAllBonus(Client c1){
        Bonus_DAO bonus_dao = new Bonus_DAO();
        return bonus_dao.CarregarBonus_pCPF(c1);
    }
    
}
