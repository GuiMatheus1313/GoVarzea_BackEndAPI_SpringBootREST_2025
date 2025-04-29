package com.govarzeasocial.social.controller;

import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/all")
    public ResponseEntity<List<Pessoa>> findAll(){
        List<Pessoa> listPessoa = pessoaService.findall();
        return ResponseEntity.ok().body(listPessoa);
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable String id){
        return ResponseEntity.ok().body(pessoaService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa){
        pessoa = pessoaService.insert(pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable String id, @RequestBody Pessoa pessoa){
        pessoa = pessoaService.edit(id, pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        return ResponseEntity.ok().body(pessoaService.delete(id));

    }
}
