package br.com.cursojava.clube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<Atividade> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Atividade listarId(@PathVariable Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @PostMapping
    public Atividade adicionar(@RequestBody Atividade atividade) {
        return repositorio.save(atividade);
    }

    @PutMapping("/{id}")
    public Atividade alterar(@PathVariable Long id, @RequestBody Atividade atividade) {
        Atividade atividadeExistente = repositorio.findById(id).orElse(null);
        if (atividadeExistente != null) {
            atividadeExistente.setNome(atividade.getNome());
            atividadeExistente.setDescricao(atividade.getDescricao());
            return repositorio.save(atividadeExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id) {
        Atividade atividadeExistente = repositorio.findById(id).orElse(null);
        if (atividadeExistente != null) {
            repositorio.delete(atividadeExistente);
            return "Atividade excluída com sucesso";
        }
        return "Atividade não localizada";
    }
}