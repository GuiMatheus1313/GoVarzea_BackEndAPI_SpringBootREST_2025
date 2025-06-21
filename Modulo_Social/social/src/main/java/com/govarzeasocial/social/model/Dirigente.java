package com.govarzeasocial.social.model;

import com.govarzeasocial.social.model.enums.Role;
import jakarta.persistence.Entity;

@Entity
public class Dirigente extends Pessoa {
    private String cargo;

    public Dirigente(){
        super();
    }

    public Dirigente(String cpf, String nome, String email, String telefone, String senha, String cargo) {
        super(cpf, nome, email, telefone, senha);
        this.cargo = cargo;
    }

    public Dirigente(String cpf, String nome, String email, String telefone, String senha, Role tipoPerfil, String cargo) {
        super(cpf, nome, email, telefone, senha, tipoPerfil);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
