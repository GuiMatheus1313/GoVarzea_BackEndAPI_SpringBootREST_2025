package com.govarzeasocial.social.service;

import com.govarzeasocial.social.dto.AuthenticationRequest;
import com.govarzeasocial.social.dto.AuthenticationResponse;
import com.govarzeasocial.social.model.Pessoa;
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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("AuthenticationService.authenticate(-)");

        Pessoa pessoa = pessoaRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));

        boolean senhaCorreta = PasswordUtil.matches(request.getPassword(), pessoa.getSenha());

        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida");
        }

        String jwtToken = jwtUtil.generateToken(pessoa);

        return new AuthenticationResponse(jwtToken);
    }
}
