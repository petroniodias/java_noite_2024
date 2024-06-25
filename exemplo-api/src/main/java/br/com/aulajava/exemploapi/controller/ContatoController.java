package br.com.aulajava.exemploapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aulajava.exemploapi.model.Contato;
import br.com.aulajava.exemploapi.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
@CrossOrigin
public class ContatoController {
    @Autowired
    private ContatoRepository repositorio;

    @GetMapping
    public List<Contato> listar(){
        return repositorio.findAll();
    }

    @PostMapping
    public Contato adicionar(@RequestBody Contato contato){
        return repositorio.save(contato);
    }
    
    @PutMapping
    public Contato alterar(@RequestBody Contato contato){
        if(contato.getId() != null && contato.getId() > 0) {
            Contato contatoExistente = repositorio.findById(contato.getId()).orElse(null);
            if (contatoExistente != null) {
                contatoExistente.setNome(contato.getNome());
                contatoExistente.setEmail(contato.getEmail());
                contatoExistente.setTelefone(contato.getTelefone());

                return repositorio.save(contatoExistente);
            }
        }
        return null;
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirContato(@PathVariable Long id) {
        Contato contatoExistente = repositorio.findById(id).orElse(null);
        if (contatoExistente != null) {
            repositorio.delete(contatoExistente);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}