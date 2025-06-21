package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.dto.TimeJogadoresResponseDTO;
import com.govarzeasocial.social.model.Time;
import com.govarzeasocial.social.model.TimeJogadoresPK;
import com.govarzeasocial.social.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/time")
@Tag(name = "Time", description = "Endpoints de Time")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @Operation(summary = "Listar todos os Times", description = "Retorna a lista de todos os times cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<List<Time>> findAll() {
        List<Time> times = timeService.findAll();
        return ResponseEntity.ok().body(times);
    }

    @Operation(summary = "Buscar um Time por ID", description = "Retorna os dados de um Time específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Time não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Time> findById(@Parameter(description = "ID do Time", required = true) @PathVariable Long id) {
        return ResponseEntity.ok().body(timeService.findById(id));
    }

    @Operation(summary = "Criar um novo Time", description = "Cadastra um novo Time.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time criado com sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<Time> insert(@RequestBody Time time) {
        Time novoTime = timeService.insert(time);
        return ResponseEntity.ok().body(novoTime);
    }

    @Operation(summary = "Atualizar um Time", description = "Edita os dados de um Time já existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time atualizado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Time não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Time> update(@PathVariable Long id, @RequestBody Time time) {
        Time updatedTime = timeService.edit(id, time);
        return ResponseEntity.ok().body(updatedTime);
    }

    @Operation(summary = "Deletar um Time", description = "Remove um Time pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time deletado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Time não encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(timeService.delete(id));
    }

    @GetMapping("/listar-time-jogadores")
    public ResponseEntity<List<TimeJogadoresResponseDTO>> listarTimesComJogadores() {
        List<TimeJogadoresResponseDTO> lista = timeService.listarTimesComJogadores();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/adicionar-jogador")
    public ResponseEntity<String> adicionarJogador(@RequestBody TimeJogadoresPK dto) {
        timeService.adicionarJogadorAoTime(dto.getTimeIdPK(), dto.getJogadorCPFPK());
        return ResponseEntity.ok("Jogador adicionado ao time com sucesso.");
    }

    @DeleteMapping("/remover-jogador")
    public ResponseEntity<String> removerJogador(@RequestBody TimeJogadoresPK dto) {
        timeService.removerJogadorDoTime(dto.getTimeIdPK(), dto.getJogadorCPFPK());
        return ResponseEntity.ok("Jogador removido do time com sucesso.");
    }

}
