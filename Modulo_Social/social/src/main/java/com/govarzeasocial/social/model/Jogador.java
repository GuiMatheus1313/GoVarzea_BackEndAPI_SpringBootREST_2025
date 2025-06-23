package com.govarzeasocial.social.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.govarzeasocial.social.model.enums.Role;
import jakarta.persistence.*;

@Entity
public class Jogador{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long jogadorID;

    public Long getJogadorID() {
        return jogadorID;
    }

    public void setJogadorID(Long jogadorID) {
        this.jogadorID = jogadorID;
    }

    @Column(unique = true)
    private String cpf;
    private String apelido;
    private String numeroCamisa;

    @MapsId
    @OneToOne
    @JoinColumn(name = "fkCpf")
    @JsonIgnore
    private Pessoa pessoa;

    public Jogador() {
    }

    public Jogador(String apelido, String numeroCamisa, Pessoa pessoa) {
        this.apelido = apelido;
        this.numeroCamisa = numeroCamisa;
        this.pessoa = pessoa;
        this.cpf = pessoa.getCpf();
    }

    /*
    public Jogador(String cpf, String nome, String email, String telefone, String senha, String apelido, String numeroCamisa) {
        super(cpf, nome, email, telefone, senha);
        this.apelido = apelido;
        this.numeroCamisa = numeroCamisa;
    }

    public Jogador(String cpf, String nome, String email, String telefone, String senha, Role tipoPerfil, String apelido, String numeroCamisa) {
        super(cpf, nome, email, telefone, senha, tipoPerfil);
        this.apelido = apelido;
        this.numeroCamisa = numeroCamisa;
    }
     */

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

    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
