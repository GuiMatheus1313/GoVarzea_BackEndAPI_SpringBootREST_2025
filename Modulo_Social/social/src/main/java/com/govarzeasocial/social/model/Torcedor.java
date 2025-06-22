package com.govarzeasocial.social.model;

import com.govarzeasocial.social.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Torcedor {

    private String biografia;

    @Id
    private String fkCpf;
    public Torcedor() {
    }

    public Torcedor(String biografia, String fkCpf) {
        this.biografia = biografia;
        this.fkCpf = fkCpf;
    }

    public String getFkCpf() {
        return fkCpf;
    }

    public void setFkCpf(String fkCpf) {
        this.fkCpf = fkCpf;
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
}
