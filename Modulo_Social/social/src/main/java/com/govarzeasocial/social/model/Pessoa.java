package com.govarzeasocial.social.model;

import com.govarzeasocial.social.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Pessoa {


    @Id
    private String cpf;

    private String nome;

    private String email;

    private String telefone;

    private String senha;

    //
    @Enumerated(EnumType.STRING)
    private Role tipoPerfil;

    public Pessoa() {
    }

    public Pessoa(String cpf, String nome, String email, String telefone, String senha, Role role) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.tipoPerfil = role;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Role tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }
}
