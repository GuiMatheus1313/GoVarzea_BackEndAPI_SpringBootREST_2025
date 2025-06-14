package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.service.DirigenteService;
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
@RequestMapping("/v1/dirigente")
@Tag(name = "Dirigente", description = "Endpoints de Dirigente")
public class DirigenteController {
    @Autowired
    private DirigenteService dirigenteService;

    @Operation(summary = "Lista todos os dirigentes", description = "Requer papel de DIRIGENTE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de dirigentes retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasRole('DIRIGENTE')")
    @GetMapping
    public ResponseEntity<List<Dirigente>> findAll() {
        return ResponseEntity.ok(dirigenteService.findAll());
    }

    @Operation(summary = "Busca um dirigente por CPF", description = "Requer autoridade DIRIGENTE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirigente encontrado"),
            @ApiResponse(responseCode = "404", description = "Dirigente não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasAuthority('DIRIGENTE')")
    @GetMapping("/{cpf}")
    public ResponseEntity<Dirigente> findById(@PathVariable String cpf) {
        return ResponseEntity.ok(dirigenteService.findById(cpf));
    }

    @Operation(summary = "Cadastra um novo dirigente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirigente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Dirigente> insert(@RequestBody Dirigente dirigente) {
        return ResponseEntity.ok(dirigenteService.insert(dirigente));
    }
}
