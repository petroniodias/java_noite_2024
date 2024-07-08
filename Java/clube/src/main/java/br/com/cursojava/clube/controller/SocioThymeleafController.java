package br.com.cursojava.clube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.cursojava.clube.model.Socio;
import br.com.cursojava.clube.repository.SocioRepository;

@Controller
@RequestMapping("/web/socio")
public class SocioThymeleafController {

    @Autowired
    private SocioRepository repositorio;

    @GetMapping
    public String listarSocios(Model model) {
        model.addAttribute("socios", repositorio.findAll());
        return "socios";
    }

    @GetMapping("/add")
    public String adicionarSocioForm(Model model) {
        model.addAttribute("socio", new Socio());
        return "adicionarSocio";
    }

    @PostMapping("/add")
    public String adicionarSocio(Socio socio) {
        repositorio.save(socio);
        return "redirect:/web/socios";
    }

    @GetMapping("/edit")
    public String editarSocioForm(@RequestParam Long id, Model model) {
        Socio socio = repositorio.findById(id).orElse(null);
        model.addAttribute("socio", socio);
        return "editarSocio";
    }

    @PostMapping("/edit")
    public String editarSocio(Socio socio) {
        repositorio.save(socio);
        return "redirect:/web/socios";
    }

    @GetMapping("/delete")
    public String excluirSocio(@RequestParam Long id) {
        repositorio.deleteById(id);
        return "redirect:/web/socios";
    }
}