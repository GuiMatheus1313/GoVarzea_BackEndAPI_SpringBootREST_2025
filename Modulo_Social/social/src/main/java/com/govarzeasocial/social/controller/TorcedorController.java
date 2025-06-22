package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Torcedor;
import com.govarzeasocial.social.service.TorcedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/torcedores")
public class TorcedorController {
    @Autowired
    private TorcedorService torcedorService;


    @PostMapping
    public ResponseEntity<Torcedor> criarTorcedor(@RequestBody Torcedor dto) {
        Torcedor torcedor = torcedorService.insert(dto);
        return ResponseEntity.ok().body(torcedor);
    }


    @GetMapping
    public ResponseEntity<List<Torcedor>> listarTodos() {
        return ResponseEntity.ok(torcedorService.findAll());
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<Torcedor> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(torcedorService.findById(cpf));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        torcedorService.deleteById(cpf);
        return ResponseEntity.noContent().build();
    }


}
