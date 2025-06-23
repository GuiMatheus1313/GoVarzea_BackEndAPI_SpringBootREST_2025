package com.govarzeasocial.social.configuration;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Jogador;
import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.service.DirigenteService;
import com.govarzeasocial.social.service.JogadorService;
import com.govarzeasocial.social.service.PessoaService;
import com.govarzeasocial.social.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulacaoDB {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private DirigenteService dirigenteService;

    /*
    @Autowired
    private PessoaRepo pessoaRepo;
    //Repo para população ao banco
    @Autowired
    private JogadorRepo jogadorRepo;

    @Autowired
    private DirigenteRepo dirigenteRepo;
    */
    public void popularPessoa(){

        String teste = "123";
        System.out.println("Teste senha");
        System.out.println("Teste raw: " + teste);
        System.out.println("Teste encoded " + PasswordUtil.encodeSenha(teste));
        if (PasswordUtil.matches(teste, PasswordUtil.encodeSenha(teste))){
            System.out.println("Senha CORRETA");
        } else {
            System.out.println("Senha INCORRETA");
        }

        Pessoa p1 = new Pessoa("12345678900", "João da Silva", "joao@email.com", "11999990000", PasswordUtil.encodeSenha("1234"));
        Pessoa p2 = new Pessoa("98765432100", "Maria Oliveira", "maria@email.com", "11888880000", PasswordUtil.encodeSenha("12345"));
        Pessoa p3 = new Pessoa("11122233344", "Carlos Souza", "carlos@email.com", "11777770000", PasswordUtil.encodeSenha("123456"));



        pessoaService.insert(p1);
        pessoaService.insert(p2);
        pessoaService.insert(p3);

        p1 = pessoaService.findByCpf("12345678900");
        p2 = pessoaService.findByCpf("98765432100");
        p3 = pessoaService.findByCpf("11122233344");

        // Criando especializações
        Jogador jogador1 = new Jogador();
        jogador1.setPessoa(p1);
        jogador1.setApelido("Jão");
        jogador1.setNumeroCamisa("10");
        jogador1.setCpf(p1.getCpf());

        Jogador jogador2 = new Jogador();
        jogador2.setPessoa(p2);
        jogador2.setApelido("Carlão");
        jogador2.setNumeroCamisa("9");
        jogador2.setCpf(p2.getCpf());

        // Criando dirigente com relação a Pessoa
        Dirigente dirigente = new Dirigente();
        dirigente.setPessoa(p3);
        dirigente.setCargo("Presidente");
        dirigente.setCpf(p3.getCpf());

        jogadorService.insert(jogador1);
        jogadorService.insert(jogador2);
        dirigenteService.insert(dirigente);

        //Teste de mesmo CPF perfil diferente
        Dirigente jogadorparaDirigente = new Dirigente();
        jogadorparaDirigente.setPessoa(p1);
        jogadorparaDirigente.setCargo("Presidente2");
        jogadorparaDirigente.setCpf(p1.getCpf());


        dirigenteService.insert(jogadorparaDirigente);


        /*
        // Salvando especializações
        jogadorRepo.save(jogador1);
        jogadorRepo.save(jogador2);
        dirigenteRepo.save(dirigente);
        */

        /*
        /*

        */
    }
}
