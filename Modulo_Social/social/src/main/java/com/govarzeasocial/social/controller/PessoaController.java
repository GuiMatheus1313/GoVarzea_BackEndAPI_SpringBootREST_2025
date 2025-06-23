package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.configuration.PopulacaoDB;
import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Autowired
    private PopulacaoDB populacaoDB;

    /*
    @Autowired
    private PessoaRepo pessoaRepo;
    //Repo para população ao banco
    @Autowired
    private JogadorRepo jogadorRepo;

    @Autowired
    private DirigenteRepo dirigenteRepo;
    */
    @GetMapping("/popular")
    public void popularPessoa(){
        System.out.println("Populando BANCO(-)");
        populacaoDB.popularPessoa();
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
        //TODO Adicionar Mecanismos de exceções
        return ResponseEntity.ok().body(pessoaService.findByCpf(id));
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
