package com.govarzeasocial.social.model;

import jakarta.persistence.*;


@Entity
public class TimeJogadores {
    @EmbeddedId
    private TimeJogadoresPK timeJogadoresPK;

    @ManyToOne
    @MapsId("timeId")
    @JoinColumn(name = "time_id")
    private Time time;

    @ManyToOne
    @MapsId("jogadorId")
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    public TimeJogadores() {
    }

    public TimeJogadores(TimeJogadoresPK timeJogadoresPK, Time time, Jogador jogador) {
        this.timeJogadoresPK = timeJogadoresPK;
        this.time = time;
        this.jogador = jogador;
    }

    public TimeJogadores(Time time, Jogador jogador) {
        this.time = time;
        this.jogador = jogador;
        this.timeJogadoresPK = new TimeJogadoresPK(time.getIdTime(), jogador.getJogadorID());
    }

    public TimeJogadoresPK getTimeJogadoresPK() {
        return timeJogadoresPK;
    }

    public void setTimeJogadoresPK(TimeJogadoresPK timeJogadoresPK) {
        this.timeJogadoresPK = timeJogadoresPK;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}
