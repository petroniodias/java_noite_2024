package br.com.cursojava.clube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursojava.clube.model.Atividade;
import br.com.cursojava.clube.repository.AtividadeRepository;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
    
    @Autowired
    private AtividadeRepository repositorio;
    
    @GetMapping
    public List<Atividade> listar(){
        return repositorio.findAll();
    }

    @PostMapping
    public Atividade adicionar(@RequestBody Atividade atividade){
        return repositorio.save(atividade);
    }
}
