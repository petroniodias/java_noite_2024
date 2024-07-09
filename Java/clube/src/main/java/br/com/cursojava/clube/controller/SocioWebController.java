package br.com.cursojava.clube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cursojava.clube.model.Socio;
import br.com.cursojava.clube.repository.SocioRepository;

@Controller
@RequestMapping("/web/socio")
public class SocioWebController {

    @Autowired
    private SocioRepository repositorio;

    @GetMapping
    public String listar(Model model){
        List<Socio> socios = repositorio.findAll();
        model.addAttribute("socios", socios);
        return "socio/list";
    }
    
    @GetMapping(value = "/{id}")
    public String listarId(@PathVariable Long id, Model model){
        Socio socio = repositorio.findById(id).orElse(null);
        model.addAttribute("socio", socio);
        return "socio/detail";
    }

    @GetMapping("/novo")
    public String novoSocioForm(Model model) {
        model.addAttribute("socio", new Socio());
        return "socio/form";
    }

    @PostMapping
    public String adicionar(Socio socio) {
        repositorio.save(socio);
        return "redirect:/web/socio";
    }
}