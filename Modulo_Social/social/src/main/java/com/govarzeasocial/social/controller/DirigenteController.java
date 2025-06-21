package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Jogador;
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

    @Operation(summary = "Atualiza um dirigente existente", description = "Requer autoridade DIRIGENTE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirigente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Dirigente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasAuthority('DIRIGENTE')")
    @PutMapping("/{cpf}")
    public ResponseEntity<Dirigente> update(@PathVariable String cpf, @RequestBody Dirigente dirigente) {
        return ResponseEntity.ok(dirigenteService.edit(cpf, dirigente));
    }

    @Operation(summary = "Exclui um dirigente existente", description = "Requer autoridade DIRIGENTE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dirigente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Dirigente não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido - sem permissão")
    })
    @PreAuthorize("hasAuthority('DIRIGENTE')")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> delete(@PathVariable String cpf) {
        return ResponseEntity.ok().body(dirigenteService.delete(cpf));
    }


    @GetMapping("/buscaNome")
    public ResponseEntity<List<Dirigente>> encontraNome(@RequestParam(required = false) String nome){
        List<Dirigente> dirigentes = dirigenteService.findByNome(nome);
        return ResponseEntity.ok().body(dirigentes);
    }


}
