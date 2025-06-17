package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Jogador;
import com.govarzeasocial.social.service.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/jogador")
@Tag(name = "Jogadores", description = "Endpoints de Jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Operation(summary = "Lista todos os Jogadores", description = "Requer papel de JOGADOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de jogadores retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasRole('JOGADOR')")
    @GetMapping
    public ResponseEntity<List<Jogador>> findAll() {
        return ResponseEntity.ok(jogadorService.findAll());
    }

    @Operation(summary = "Busca um jogador por CPF", description = "Requer autoridade JOGADOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador encontrado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasAuthority('JOGADOR')")
    @GetMapping("/{cpf}")
    public ResponseEntity<Jogador> findById(@PathVariable String cpf) {
        return ResponseEntity.ok(jogadorService.findById(cpf));
    }

    @Operation(summary = "Cadastra um novo jogador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "jogador criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),

    })
    @PostMapping
    public ResponseEntity<Jogador> insert(@RequestBody Jogador jogador) {
        return ResponseEntity.ok(jogadorService.insert(jogador));
    }

    @Operation(summary = "Atualiza um jogador existente", description = "Requer autoridade JOGADOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasAuthority('JOGADOR')")
    @PutMapping("/{cpf}")
    public ResponseEntity<Jogador> update(@PathVariable String cpf, @RequestBody Jogador jogador) {
        return ResponseEntity.ok(jogadorService.edit(cpf, jogador));
    }

    @Operation(summary = "Exclui um jogador existente", description = "Requer autoridade JOGADOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Jogador excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasAuthority('JOGADOR')")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> delete(@PathVariable String cpf) {
        return ResponseEntity.ok().body(jogadorService.delete(cpf));
    }


}
