package com.mycompany.estadoecidade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



public class Cidade {
    
  
    private long id ;

    private String nome;

    
    private Estado estado;

    public Cidade(){}

    public Cidade(String nome, Estado estado){
        this.nome = nome;
        this.estado = estado;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    @Override
    public String toString() {
    return nome + " - " + estado.getUf();
}
}

