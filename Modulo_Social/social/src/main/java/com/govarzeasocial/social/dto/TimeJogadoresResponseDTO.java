package com.govarzeasocial.social.dto;

import com.govarzeasocial.social.model.Jogador;

import java.time.LocalDate;
import java.util.List;

public class TimeJogadoresResponseDTO {
    private Long idTime;
    private String nome;
    private String localizacao;
    private LocalDate fundacao;
    private String fkDirigente;
    private List<Jogador> jogadores;

    public TimeJogadoresResponseDTO() {
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

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
