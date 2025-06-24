package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/loginPessoa")
@Tag(name = "Login(DEPRECIADA)", description = "Endpoints de Login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Operation(summary = "Fazer Login da PESSOA", description = "Inserção das credenciais para Fazer Login da PESSOA")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso em Login", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "403", description = "Credenciais Erradas", content = @Content)
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Pessoa usuario){
        boolean check = loginService.loginSimples(usuario);
        if(check)
            return new ResponseEntity<Boolean>(check, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(check, HttpStatus.UNAUTHORIZED);
    }
}
