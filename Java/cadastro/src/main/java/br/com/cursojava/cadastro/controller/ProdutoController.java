package br.com.cursojava.cadastro.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.cursojava.cadastro.model.Produto;
import br.com.cursojava.cadastro.repository.ProdutoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository repositorio;

    @GetMapping
    public List<Produto> listar(){
        return repositorio.findAll();
    }    
}
