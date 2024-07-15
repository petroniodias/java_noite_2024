package br.com.cursojava.clube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cursojava.clube.model.Atividade;
import br.com.cursojava.clube.repository.AtividadeRepository;

@Controller
@RequestMapping("/web/atividade")
public class AtividadeWebController {

    @Autowired
    private AtividadeRepository repositorio;

    @GetMapping
    public String listar(Model model){
        List<Atividade> atividades = repositorio.findAll();
        model.addAttribute("atividades", atividades);
        return "atividade/list";
    }
    
    @GetMapping(value = "/{id}")
    public String listarId(@PathVariable Long id, Model model){
        Atividade atividade = repositorio.findById(id).orElse(null);
        model.addAttribute("atividade", atividade);
        return "atividade/detail";
    }

    
    @GetMapping("/nova")
    public String novaAtividadeForm(Model model) {
        model.addAttribute("atividade", new Atividade());
        return "atividade/form";
    }

    @PostMapping
    public String adicionar(Atividade atividade) {
        repositorio.save(atividade);
        return "redirect:/web/atividade";
    }

    @GetMapping("/editar/{id}")
    public String editarAtividadeForm(@PathVariable Long id, Model model) {
        Atividade atividade = repositorio.findById(id).orElse(null);
        if (atividade != null) {
            model.addAttribute("atividade", atividade);
            return "atividade/edit";
        }
        return "redirect:/web/atividade";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAtividade(@PathVariable Long id) {
        Atividade atividade = repositorio.findById(id).orElse(null);
        if (atividade != null) {
            repositorio.delete(atividade);
        }
        return "redirect:/web/atividade";
    }
    /*

    @GetMapping("/editar/{id}")
    public String editarSocioForm(@PathVariable Long id, Model model) {
        Socio socio = repositorio.findById(id).orElse(null);
        if (socio != null) {
            model.addAttribute("socio", socio);
            return "socio/edit";
        }
        return "redirect:/web/socio";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarSocio(@PathVariable Long id, Socio socioAtualizado) {
        Socio socio = repositorio.findById(id).orElse(null);
        if (socio != null) {
            socio.setNome(socioAtualizado.getNome());
            socio.setEndereco(socioAtualizado.getEndereco());
            socio.setCpf(socioAtualizado.getCpf());
            socio.setTelefone(socioAtualizado.getTelefone());
            socio.setEmail(socioAtualizado.getEmail());
            repositorio.save(socio);
        }
        return "redirect:/web/socio";
    }
    */
}
