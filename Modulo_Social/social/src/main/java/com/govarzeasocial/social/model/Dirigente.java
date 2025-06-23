package com.govarzeasocial.social.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.govarzeasocial.social.model.enums.Role;
import jakarta.persistence.*;

@Entity
public class Dirigente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long dirigenteID;

    public Long getDirigenteID() {
        return dirigenteID;
    }

    public void setDirigenteID(Long dirigenteID) {
        this.dirigenteID = dirigenteID;
    }

    @Column(unique = true)
    private String cpf;
    private String cargo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "fkCpf")
    @JsonIgnore
    private Pessoa pessoa;

    public Dirigente(){

    }

    public Dirigente(String cargo, Pessoa pessoa) {
        this.cpf = pessoa.getCpf();
        this.cargo = cargo;
        this.pessoa = pessoa;
    }

    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }




    /*
    public Dirigente(String cpf, String nome, String email, String telefone, String senha, String cargo) {
        super(cpf, nome, email, telefone, senha);
        this.cargo = cargo;
    }

    public Dirigente(String cpf, String nome, String email, String telefone, String senha, Role tipoPerfil, String cargo) {
        super(cpf, nome, email, telefone, senha, tipoPerfil);
        this.cargo = cargo;
    }
     */

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
