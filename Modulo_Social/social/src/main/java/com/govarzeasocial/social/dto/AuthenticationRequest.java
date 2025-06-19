package com.govarzeasocial.social.dto;


import com.govarzeasocial.social.model.enums.Role;

public class AuthenticationRequest {
    private String email;
    private String password;

    private Role tipoPerfil;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String password, Role tipoPerfil) {
        this.email = email;
        this.password = password;
        this.tipoPerfil = tipoPerfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Role tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }
}
