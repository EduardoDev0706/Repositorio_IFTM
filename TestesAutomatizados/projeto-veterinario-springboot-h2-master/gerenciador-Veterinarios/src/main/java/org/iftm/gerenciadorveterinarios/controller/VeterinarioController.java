package org.iftm.gerenciadorveterinarios.controller;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.services.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VeterinarioController {
    
    @Autowired
    private VeterinarioService servico;

    // Acessa o formulário de consulta de veterinários    
    @GetMapping("/find")
    public String veterinariosFind(Model model) {
        return "findVeterinarioForm"; 	
    }
    
    // Acessa o formulário de cadastro de veterinários
    @GetMapping("/form")
    public String veterinariosForm(Veterinario veterinario) {    	
        return "addVeterinarioForm";
    }

    // Adiciona novo veterinário
    @PostMapping("/add")
    public String novo(Veterinario veterinario) {
        servico.salvar(veterinario);
        return "redirect:/home";
    }

    // Acessa o formulário de edição com base no ID
    @GetMapping("/form/{id}")
    public String updateForm(Model model, @PathVariable int id) {
        Optional<Veterinario> veterinario = servico.buscaVeterinariosPeloId(id);
        if (veterinario.isPresent()) {
            model.addAttribute("veterinario", veterinario.get());
            return "atualizaVeterinarioForm";
        } else {
            return "redirect:/home";
        }
    }

    @PostMapping("/update/{id}")
    public String atualizar(Veterinario veterinario, @PathVariable int id) {
        servico.salvar(veterinario);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {  
        try {
            servico.apagar(id); // Passa apenas o ID. A regra de negócio de proteção está na camada de Serviço.
        } catch (RuntimeException e) {
            // Captura a exceção caso o ID informado não exista no banco de dados, evitando crash na aplicação
            System.err.println(e.getMessage());
        }        
        return "redirect:/home";
    }

}