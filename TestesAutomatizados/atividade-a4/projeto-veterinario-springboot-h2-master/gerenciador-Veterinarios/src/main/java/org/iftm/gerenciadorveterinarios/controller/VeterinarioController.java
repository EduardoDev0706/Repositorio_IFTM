package org.iftm.gerenciadorveterinarios.controller;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.iftm.gerenciadorveterinarios.servicies.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// @Controller é associada com classes que possuem métodos que processam requests (requisições) numa aplicação web.
@Controller
public class VeterinarioController {
	
	// O método abaixo faz injeção de dependência da classe Repository
    @Autowired
    private VeterinarioService servico;

    // Faz o mapeamento para a requisição find. Acessa o formulario de consulta de veterinários    
    @GetMapping("/find")
    public String veterinariosFind(Model model) {
    	return "findVeterinarioForm"; 	
    }
    
    
    // Faz o mapeamento para a requisição form. Acessa o formulario de cadastro de animais
    @GetMapping("/form")
    public String veterinariosForm(Veterinario veterinario) {    	
        return "addVeterinarioForm";
    }

    // Faz o mapeamento da requisição add. Adiciona novo veterinario
    @PostMapping("/add")
    public String novo(Veterinario veterinario) {
        servico.salvar(veterinario);
        return "redirect:/home";
    }

    // faz o mapeamento da requisição form com o parametro id. Acessa o formulario de edição
    @GetMapping("form/{id}")
    public String updateForm(Model model, @PathVariable int id) {
    	Optional<Veterinario> veterinario = servico.buscaVeterinariosPeloId(id);
    	if (veterinario.isPresent()) {
    		model.addAttribute("veterinario", veterinario.get());
    		return "atualizaVeterinarioForm";
    	}else {
    		return "redirect:/home";
    	}
    }

    // faz o mapeamento da requisição update. Atualiza veterinario
    @PostMapping("update/{id}")
    public String alterarProduto(Veterinario veterinario, @PathVariable int id) {
        servico.salvar(veterinario);
        return "redirect:/home";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {  
    	Optional<Veterinario> veterinario = servico.buscaVeterinariosPeloId(id);
    	if (veterinario.isPresent()) {
            servico.apagar(veterinario.get());
    	}        
        return "redirect:/home";
    }

}
