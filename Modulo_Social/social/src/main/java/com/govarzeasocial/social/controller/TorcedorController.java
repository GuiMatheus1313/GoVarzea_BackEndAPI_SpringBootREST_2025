package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Torcedor;
import com.govarzeasocial.social.service.TorcedorService;
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
@RequestMapping("v1/torcedor")
@Tag(name = "Torcedores", description = "Endpoints de Torcedor")
public class TorcedorController {
    @Autowired
    private TorcedorService torcedorService;


    @Operation(summary = "Listar todos os torcedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de torcedores retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar torcedores")
    })
    @GetMapping
    public ResponseEntity<List<Torcedor>> listarTodos() {
        return ResponseEntity.ok(torcedorService.findAll());
    }

    @Operation(summary = "Buscar torcedor pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torcedor encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Torcedor não encontrado com o CPF informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar torcedor")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<Torcedor> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(torcedorService.findByCpf(cpf));
    }

    @Operation(summary = "Procurar Torcedor pelo seu nome", description = "NÃO REQUER AUTORIDADE TORCEDOR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torcedores encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida: nome não informado ou inválido"),
            @ApiResponse(responseCode = "404", description = "Nenhum torcedor encontrado com esse nome"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar torcedor")
    })
    @GetMapping("/buscaNome")
    public ResponseEntity<List<Torcedor>> buscarPorNome(@RequestParam(required = false) String nome){
        List<Torcedor> torcedores = torcedorService.findByNome(nome);
        return ResponseEntity.ok().body(torcedores);
    }

    @Operation(summary = "Criar um novo torcedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torcedor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: dados obrigatórios faltando)"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao criar torcedor")
    })
    @PostMapping
    public ResponseEntity<Torcedor> criarTorcedor(@RequestBody Torcedor dto) {
        Torcedor torcedor = torcedorService.insert(dto);
        return ResponseEntity.ok().body(torcedor);
    }

    @Operation(summary = "Atualizar torcedor por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torcedor atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (ex: dados inválidos ou mal formatados)"),
            @ApiResponse(responseCode = "404", description = "Torcedor com o ID informado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar torcedor")
    })
    @PreAuthorize("hasAuthority('TORCEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<Torcedor> update(@PathVariable String id, @RequestBody Torcedor torcerdor) {
        Torcedor updatedTorcedor = torcedorService.edit(id, torcerdor);
        return ResponseEntity.ok().body(updatedTorcedor);
    }

    @Operation(summary = "Deletar torcedor por CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Torcedor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Torcedor com o CPF informado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao deletar torcedor")
    })
    @PreAuthorize("hasAuthority('TORCEDOR')")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletar(@PathVariable String cpf) {
        return ResponseEntity.ok().body(torcedorService.delete(cpf));
    }

}
