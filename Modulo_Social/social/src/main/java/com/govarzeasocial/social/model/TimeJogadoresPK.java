package com.govarzeasocial.social.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TimeJogadoresPK {
    private Long timeId;
    private Long jogadorId;

    public TimeJogadoresPK() {
    }

    public TimeJogadoresPK(Long timeId, Long jogadorId) {
        this.timeId = timeId;
        this.jogadorId = jogadorId;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public Long getJogadorId() {
        return jogadorId;
    }

    public void setJogadorId(Long jogadorId) {
        this.jogadorId = jogadorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeJogadoresPK that = (TimeJogadoresPK) o;
        return Objects.equals(timeId, that.timeId) && Objects.equals(jogadorId, that.jogadorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeId, jogadorId);
    }
}
