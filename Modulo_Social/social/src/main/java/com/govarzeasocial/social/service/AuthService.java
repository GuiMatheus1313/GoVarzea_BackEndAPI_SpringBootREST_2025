package com.govarzeasocial.social.service;

import com.govarzeasocial.social.dto.AuthenticationRequest;
import com.govarzeasocial.social.dto.AuthenticationResponse;
import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.model.enums.Role;
import com.govarzeasocial.social.repository.PessoaRepo;
import com.govarzeasocial.social.util.JwtUtil;
import com.govarzeasocial.social.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PessoaRepo pessoaRepo;

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private DirigenteService dirigenteService;

    @Autowired
    private TorcedorService torcedorService;



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("AuthenticationService.authenticate(-)");


        Pessoa pessoa = pessoaRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));

        boolean senhaCorreta = PasswordUtil.matches(request.getPassword(), pessoa.getSenha());

        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida");
        }

        //Se é perfil válido
        if(!verifyPerfil(pessoa.getCpf(), request.getTipoPerfil())){
            throw new RuntimeException("O perfil informado não corresponde ao tipo real da pessoa.");
        }

        String jwtToken = jwtUtil.generateToken(pessoa, request.getTipoPerfil());

        return new AuthenticationResponse(jwtToken);
    }

    public boolean verifyPerfil(String cpf, Role tipoPerfil) {
        switch (tipoPerfil) {
            case ROLE_JOGADOR:
                return jogadorService.checkJogador(cpf);

            case ROLE_DIRIGENTE:
                return dirigenteService.checkDirigente(cpf);

            case ROLE_TORCEDOR:
                return torcedorService.checkTorcedor(cpf);
            default:
                return false;
        }
    }
}
