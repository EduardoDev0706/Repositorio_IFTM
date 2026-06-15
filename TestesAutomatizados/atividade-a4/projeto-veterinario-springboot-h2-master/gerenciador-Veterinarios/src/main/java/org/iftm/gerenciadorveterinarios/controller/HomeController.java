package org.iftm.gerenciadorveterinarios.controller;

import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.iftm.gerenciadorveterinarios.servicies.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	// O método abaixo faz injeção de dependência da classe Repository
	@Autowired
	private VeterinarioService servico;

	// Método responsável em mapear a requisição /home
	// Ele pode receber o parâmetro nome, caso tenha sido chamado pela função de pesquisa.
	@GetMapping("/home")
	public String home(@RequestParam(value = "nome", required = false) Optional<String> nome, Model model) {
		List<Veterinario> veterinarios;
		if (nome.isPresent()) {
			veterinarios = servico.buscaVeterinariosComParteNome(nome.get());
		} else {
			veterinarios = servico.buscaTodosVeterinarios();
		}
		model.addAttribute("veterinarios", veterinarios);
		return "home";
	}

}
