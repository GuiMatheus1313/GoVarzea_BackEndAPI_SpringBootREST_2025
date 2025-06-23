package com.govarzeasocial.social.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.govarzeasocial.social.model.enums.Role;
import jakarta.persistence.*;

@Entity
public class Torcedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long torcedorID;



    @Column(unique = true)
    private String cpf;

    private String biografia;

    @OneToOne
    @MapsId
    @JoinColumn(name = "fkCpf")
    @JsonIgnore
    private Pessoa pessoa;

    public Torcedor() {
    }

    public Torcedor(String biografia, Pessoa pessoa) {
        this.cpf = pessoa.getCpf();
        this.biografia = biografia;
        this.pessoa = pessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Torcedor(String cpf, String biografia, Pessoa pessoa) {
        this.cpf = cpf;
        this.biografia = biografia;
        this.pessoa = pessoa;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /*
    public Torcedor(String cpf, String nome, String email, String telefone, String senha) {
        super(cpf, nome, email, telefone, senha);
    }

    public Torcedor(String cpf, String nome, String email, String telefone, String senha, Role tipoPerfil) {
        super(cpf, nome, email, telefone, senha, tipoPerfil);
    }

     */

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getTorcedorID() {
        return torcedorID;
    }

    public void setTorcedorID(Long torcedorID) {
        this.torcedorID = torcedorID;
    }
}
