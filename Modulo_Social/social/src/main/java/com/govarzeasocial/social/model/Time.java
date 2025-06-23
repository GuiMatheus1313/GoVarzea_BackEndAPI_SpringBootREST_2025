package com.govarzeasocial.social.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTime;
    private String nome;
    private String localizacao;
    private LocalDate fundacao;

    @ManyToOne
    @JoinColumn(name = "fkDirigente", referencedColumnName = "fkCpf")
    private Dirigente dirigente;

    public Time() {
    }


    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public void setFundacao(LocalDate fundacao) {
        this.fundacao = fundacao;
    }

    public Dirigente getDirigente() {
        return dirigente;
    }

    public void setDirigente(Dirigente dirigente) {
        this.dirigente = dirigente;
    }
}
