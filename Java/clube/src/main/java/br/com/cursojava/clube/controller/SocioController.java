package br.com.cursojava.clube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursojava.clube.model.Socio;
import br.com.cursojava.clube.repository.SocioRepository;

@RestController
@RequestMapping("/socio")
public class SocioController {

    @Autowired
    private SocioRepository repositorio;

    @GetMapping
    public List<Socio> listar(){
        return repositorio.findAll();
    }

    @PostMapping
    public Socio adicionar(@RequestBody Socio socio) {
        return repositorio.save(socio);
    }

    @DeleteMapping(value = "/{id}")
    public String excluir(@PathVariable Long id){
        Socio socioExistente=repositorio.findById(id).orElse(null);
        if (socioExistente != null){
            repositorio.delete(socioExistente);
            return "Socio excluido com sucesso";
        }
        return "Socio não localizado";
    }
}
