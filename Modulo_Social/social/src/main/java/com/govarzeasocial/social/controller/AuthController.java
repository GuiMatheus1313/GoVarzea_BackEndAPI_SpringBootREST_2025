package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.dto.AuthenticationRequest;
import com.govarzeasocial.social.dto.AuthenticationResponse;
import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.repository.PessoaRepo;
import com.govarzeasocial.social.service.AuthService;
import com.govarzeasocial.social.util.JwtUtil;
import com.govarzeasocial.social.util.PasswordUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("*")
@Tag(name = "Autenticação", description = "Endpoints de Autenticação JWT")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest request){
        System.out.println("createToken(-)");
        return authService.authenticate(request);
    }
}
