package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // GET /produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id)
        );
    }

    // POST /produtos
    @PostMapping
    public ResponseEntity<Produto> cadastrar(
            @Valid @RequestBody Produto produto) {

        Produto novoProduto = service.salvar(produto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoProduto);
    }

    // PUT /produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Produto produto) {

        Produto produtoAtualizado =
                service.atualizar(id, produto);

        return ResponseEntity.ok(produtoAtualizado);
    }

    // DELETE /produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
