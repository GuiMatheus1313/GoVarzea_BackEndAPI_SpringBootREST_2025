package com.govarzeasocial.social.dto;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Jogador;

import java.time.LocalDate;
import java.util.List;

public class TimeJogadoresResponseDTO {
    private Long idTime;
    private String nome;
    private String localizacao;
    private LocalDate fundacao;

    private Dirigente fkDirigente;

    private List<Jogador> jogadores;

    public TimeJogadoresResponseDTO() {
    }

    public TimeJogadoresResponseDTO(Long idTime, String nome, String localizacao, LocalDate fundacao, Dirigente fkDirigente, List<Jogador> jogadores) {
        this.idTime = idTime;
        this.nome = nome;
        this.localizacao = localizacao;
        this.fundacao = fundacao;
        this.fkDirigente = fkDirigente;
        this.jogadores = jogadores;
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

    public Dirigente getFkDirigente() {
        return fkDirigente;
    }

    public void setFkDirigente(Dirigente fkDirigente) {
        this.fkDirigente = fkDirigente;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
