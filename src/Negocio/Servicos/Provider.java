/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Servicos;

import Banco.Cadastros.Fornecedor_DAO;
import java.util.List;

/**
 *
 * @author dcet1-lami11-ubuntu
 */
public class Provider {
    private String  cnpj, nome, telefone;
    
    public Provider(String cnpj, String nome, String telefone){
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
    }
    
    public Provider(){}
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public boolean Inserir(){
        Fornecedor_DAO fornecedor_dao = new Fornecedor_DAO();
        return fornecedor_dao.Inserir(this);
    }
    
    public List<Provider> Carregar(){
        Fornecedor_DAO fornecedor_dao = new Fornecedor_DAO();
        return fornecedor_dao.Carregar();
    }
} //END CLASS
