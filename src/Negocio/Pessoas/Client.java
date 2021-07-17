package Negocio.Pessoas;

import Banco.Cadastros.Bonus_DAO;
import Banco.Cadastros.Pessoa_DAO;
import Negocio.Servicos.Bonus;
import java.util.List;

public class Client extends Person{
    protected String cpf, email;
    protected Bonus bonus;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
    
   
    public boolean CheckClient(Client client){
        return client.getCpf().equals(this.cpf);
    }
   
     public Bonus Buscar_myBonus(){
       Bonus_DAO bonus_dao = new Bonus_DAO();
       bonus = bonus_dao.Buscar_pCpf(this);
       return bonus;
   }
   
    public Bonus Buscar_myBonus(String data){
       Bonus_DAO bonus_dao = new Bonus_DAO();
       bonus = bonus_dao.Buscar_pCpf(this, data);
       return bonus;
   }
    
   public boolean AttBonus_Situacao(){
       if(bonus.getValue() != 0.0f){
            Bonus_DAO bonus_dao = new Bonus_DAO();
            bonus_dao.Atualizar_Situacao(bonus);
            return true;
       }
       return false;
   }
   
   public boolean Inserir_Bonus(){
       Bonus_DAO bonus_dao = new Bonus_DAO();
       return bonus_dao.Inserir(bonus, this);
   }
    
   public boolean Inserir(){
       Pessoa_DAO pessoa_dao = new Pessoa_DAO();
       return pessoa_dao.Inserir(this);
   }
   
   public Client getMe(){
       Pessoa_DAO pessoa_dao = new Pessoa_DAO();
       return pessoa_dao.Buscar_pCpf(cpf);
   }
   
   public boolean Atualizar(){
       Pessoa_DAO pessoa_dao = new Pessoa_DAO();
       return pessoa_dao.Atualizar(this);
   }
   
   public boolean Excluir(){
       Pessoa_DAO pessoa_dao = new Pessoa_DAO();
       return pessoa_dao.Excluir(this);
   }
  
    public boolean Alterar_Senha(){
       Pessoa_DAO pessoa_dao = new Pessoa_DAO();
       return pessoa_dao.Alterar_Senha(this);
   }
   
    public Client Login(){
       Pessoa_DAO pessoa_dao = new Pessoa_DAO();
       return pessoa_dao.Login(this); 
    }
    
    public List<Client> getAllClientes(){
        Pessoa_DAO pessoa_dao = new Pessoa_DAO();
        return pessoa_dao.CarregarDados(this);
    }
}
