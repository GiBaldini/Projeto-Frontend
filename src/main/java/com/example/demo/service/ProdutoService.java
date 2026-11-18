package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // CREATE e READ ALL
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    // READ BY ID
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                    )
                );
    }

    // CREATE
    public Produto salvar(Produto produto) {
        produto.setId(null);
        return repository.save(produto);
    }

    // UPDATE
    public Produto atualizar(Long id, Produto dados) {

        Produto produto = buscarPorId(id);

        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());

        return repository.save(produto);
    }

    // DELETE
    public void excluir(Long id) {

        Produto produto = buscarPorId(id);

        repository.delete(produto);
    }
}
