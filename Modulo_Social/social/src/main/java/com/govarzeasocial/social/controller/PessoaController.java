package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.service.PessoaService;
import com.govarzeasocial.social.util.PasswordUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoa")
@Tag(name = "Pessoa", description = "Endpoints de Pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostConstruct
    private void popularPessoa(){
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

    }

    @Operation(summary = "Achar TODOS Pessoa", description = "Achar todas da tabela Pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso em Achar todos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor interno", content = @Content)
            }
    )
    @GetMapping("")
    public ResponseEntity<List<Pessoa>> findAll(){
        List<Pessoa> listPessoa = pessoaService.findall();
        return ResponseEntity.ok().body(listPessoa);
    }

    @Operation(summary = "Achar UMA Pessoa", description = "Achar UMA da tabela Pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso em Achar", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "404", description = "Pessoa não Encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor interno", content = @Content)
            }
    )
    @GetMapping(value ="/{id}")
    public ResponseEntity<Pessoa> findById(@Parameter (description = "Cpf da Pessoa", required = true) @PathVariable String id){
        return ResponseEntity.ok().body(pessoaService.findById(id));
    }


    @Operation(summary = "Cadastrar UMA Pessoa", description = "Cadastrar UMA da tabela Pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso em Cadastrar", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor interno", content = @Content)
            }
    )
    @PostMapping()
    public ResponseEntity<Pessoa> insert(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados Pessoa", required = true, content = @Content(schema = @Schema(implementation = Pessoa.class)))@RequestBody Pessoa pessoa){
        pessoa = pessoaService.insert(pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @Operation(summary = "Editar UMA Pessoa", description = "Editar UMA da tabela Pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso em Editar", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor interno", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@Parameter (description = "Cpf da Pessoa", required = true)@PathVariable String id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados Pessoa", required = true, content = @Content(schema = @Schema(implementation = Pessoa.class))) @RequestBody Pessoa pessoa){
        pessoa = pessoaService.edit(id, pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @Operation(summary = "Deletar UMA Pessoa", description = "Deletar UMA da tabela Pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso em Deletar", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class))),
                    @ApiResponse(responseCode = "500", description = "Erro no servidor interno", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Parameter (description = "Cpf da Pessoa", required = true) @PathVariable String id){
        return ResponseEntity.ok().body(pessoaService.delete(id));

    }
}
