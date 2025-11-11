package com.eduardoCarmo.condominio.controller;

import com.eduardoCarmo.condominio.model.Proprietario;
import com.eduardoCarmo.condominio.repository.ProprietarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProprietarioController {

    // 1. Injeta o Repository que criamos
    @Autowired
    private ProprietarioRepository proprietarioRepository;

    /**
     * Ponto 6.2 do Roteiro: Relatório proprietários
     * Mapeamento: @GetMapping(“rel-prop”)
     * Template Thymeleaf: rel_prop.html
     *
     * Este método lida com a requisição GET para /rel-prop.
     * Ele busca todos os proprietários e exibe a página.
     */
    @GetMapping("/rel-prop")
    public String exibirRelatorioProprietarios(Model model) {
        
        // 2. Busca todos os proprietários no banco de dados
        List<Proprietario> proprietarios = proprietarioRepository.findAll();

        // 3. Envia a lista para o HTML (Thymeleaf)
        model.addAttribute("proprietarios", proprietarios);

        // 4. Envia um objeto "Proprietario" vazio para o formulário de cadastro
        // Isso é necessário para o 'th:object' do Thymeleaf funcionar na página
        if (!model.containsAttribute("proprietario")) {
            model.addAttribute("proprietario", new Proprietario());
        }

        // 5. Retorna o nome do arquivo HTML (sem a extensão .html)
        return "rel_prop";
    }

    /**
     * Ponto 6.2 do Roteiro: Gravar dados novo proprietário
     * Mapeamento: @PostMapping(“cad-prop”)
     * Template Thymeleaf: rel_prop.html (em caso de sucesso, redireciona)
     *
     * Este método lida com a requisição POST de /cad-prop (envio do formulário).
     */
    @PostMapping("/cad-prop")
    public String cadastrarNovoProprietario(
            @Valid @ModelAttribute("proprietario") Proprietario proprietario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // 6. Verifica se a validação (ex: @NotBlank, @Size) encontrou erros
        if (bindingResult.hasErrors()) {
            // Se houver erros, não salva.
            // Adiciona os erros e o objeto (com os dados preenchidos) ao RedirectAttributes
            // para que o usuário possa ver o que errou.
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.proprietario", bindingResult);
            redirectAttributes.addFlashAttribute("proprietario", proprietario);
            
            // Retorna para o GET /rel-prop, mas exibindo os erros
            return "redirect:/rel-prop";
        }

        // 7. Se não houver erros, salva o novo proprietário no banco
        proprietarioRepository.save(proprietario);

        // 8. Adiciona uma mensagem de sucesso
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Proprietário cadastrado com sucesso!");

        // 9. Redireciona o navegador para a URL /rel-prop (o método GET acima)
        // Isso evita o reenvio duplicado do formulário se o usuário atualizar a página
        return "redirect:/rel-prop";
    }
}
