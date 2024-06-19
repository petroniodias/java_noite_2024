package br.com.cursojava.cadastro.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.cursojava.cadastro.model.Cliente;
import br.com.cursojava.cadastro.repository.ClienteRepository;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repositorio;

    @GetMapping
    public List<Cliente> listar(){
        return repositorio.findAll();
    }

    @GetMapping(value = "/{id}")
    public Cliente listarId(@PathVariable Long id){
        return repositorio.findById(id).get();
    }
}
