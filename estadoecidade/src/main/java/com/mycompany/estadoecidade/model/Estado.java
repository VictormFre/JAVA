package com.mycompany.estadoecidade.model;

import jakarta.persistence.*;



public class Estado {
    
    private String nome , uf;

    private long id;

    public Estado(){}  

    public Estado(String nome, String uf){ 
        this.nome = nome;
        this.uf = uf;
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getUf(){
        return uf;
    }

    public void setUf(String uf){
        this.uf = uf;
    }

    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    @Override
    public String toString() {
    return nome + " (" + uf + ")";
}
}
