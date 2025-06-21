package com.govarzeasocial.social.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

@Embeddable
public class TimeJogadoresPK {
    private Long timeIdPK;
    private String jogadorCPFPK;

    public TimeJogadoresPK() {
    }

    public TimeJogadoresPK(Long timeIdPK, String jogadorCPFPK) {
        this.timeIdPK = timeIdPK;
        this.jogadorCPFPK = jogadorCPFPK;
    }

    public Long getTimeIdPK() {
        return timeIdPK;
    }

    public void setTimeIdPK(Long timeIdPK) {
        this.timeIdPK = timeIdPK;
    }

    public String getJogadorCPFPK() {
        return jogadorCPFPK;
    }

    public void setJogadorCPFPK(String jogadorCPFPK) {
        this.jogadorCPFPK = jogadorCPFPK;
    }
}
