package com.govarzeasocial.social.model;

import com.govarzeasocial.social.model.enums.Role;
import jakarta.persistence.Entity;

@Entity
public class Jogador extends Pessoa{
    private String apelido;
    private String numeroCamisa;

    public Jogador() {
        super();
    }

    public Jogador(String cpf, String nome, String email, String telefone, String senha, String apelido, String numeroCamisa) {
        super(cpf, nome, email, telefone, senha);
        this.apelido = apelido;
        this.numeroCamisa = numeroCamisa;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(String numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }
}
