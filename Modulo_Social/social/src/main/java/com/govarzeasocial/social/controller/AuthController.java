package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.dto.AuthenticationRequest;
import com.govarzeasocial.social.dto.AuthenticationResponse;
import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.repository.PessoaRepo;
import com.govarzeasocial.social.service.AuthService;
import com.govarzeasocial.social.util.JwtUtil;
import com.govarzeasocial.social.util.PasswordUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("*")
@Tag(name = "Autenticação(JWT)", description = "Endpoints de Autenticação JWT")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Autenticar usuário e gerar JWT", description = "Endpoint público para login. Retorna o token JWT ao informar as credenciais corretas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token gerado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: campos obrigatórios faltando)"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao autenticar")
    })
    @PostMapping("/auth")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest request){
        System.out.println("createToken(-)");
        return authService.authenticate(request);
    }
}
