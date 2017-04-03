package br.unipe.hotel.entity;

import javax.persistence.Entity;

@Entity
public class Funcionario extends Pessoa{
    
    private int matricula;
    
    private String ctps;

    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public String getCtps() {
        return ctps;
    }
    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

}
