package com.govarzeasocial.social.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTime;
    private String nome;
    private String localizacao;
    private LocalDate fundacao;
    private String fkDirigente;

    public Time() {
    }

    public Time(Long idTime, String nome, String localizacao, LocalDate fundacao, String fkDirigente) {
        this.idTime = idTime;
        this.nome = nome;
        this.localizacao = localizacao;
        this.fundacao = fundacao;
        this.fkDirigente = fkDirigente;
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

    public String getFkDirigente() {
        return fkDirigente;
    }

    public void setFkDirigente(String fkDirigente) {
        this.fkDirigente = fkDirigente;
    }
}
