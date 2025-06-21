package com.govarzeasocial.social.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;


@Entity
public class TimeJogadores {
    @EmbeddedId
    private TimeJogadoresPK timeJogadoresPK;

    public TimeJogadores() {
    }

    public TimeJogadores(TimeJogadoresPK timeJogadoresPK) {
        this.timeJogadoresPK = timeJogadoresPK;
    }

    public TimeJogadoresPK getTimeJogadoresPK() {
        return timeJogadoresPK;
    }

    public void setTimeJogadoresPK(TimeJogadoresPK timeJogadoresPK) {
        this.timeJogadoresPK = timeJogadoresPK;
    }
}
