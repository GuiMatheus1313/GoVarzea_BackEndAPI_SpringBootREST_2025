package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private PessoaService pessoaService;

    public boolean loginSimples(Pessoa pessoa){
        Pessoa pessoaDb = pessoaService.findByCpf(pessoa.getCpf());

        if(pessoaDb == null){
            System.out.println("O usuário não existe no banco ainda...");
            return false;
        }

        return PasswordUtil.matches(pessoa.getSenha(), pessoaDb.getSenha());
    }
}
