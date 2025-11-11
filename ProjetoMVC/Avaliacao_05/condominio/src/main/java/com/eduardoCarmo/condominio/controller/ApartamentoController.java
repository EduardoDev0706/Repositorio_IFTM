package com.eduardoCarmo.condominio.controller;

import com.eduardoCarmo.condominio.model.Apartamento;
import com.eduardoCarmo.condominio.model.Proprietario;
import com.eduardoCarmo.condominio.repository.ApartamentoRepository;
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
public class ApartamentoController {

    // 1. Injeta AMBOS os repositórios
    @Autowired
    private ApartamentoRepository apartamentoRepository;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    /**
     * Ponto 6.2 do Roteiro: Relatório apartamentos
     * Mapeamento: @GetMapping(“rel-apto”)
     * Template Thymeleaf: rel_apto.html
     *
     * Este método lida com a requisição GET para /rel-apto.
     */
    @GetMapping("/rel-apto")
    public String exibirRelatorioApartamentos(Model model) {

        // 2. Busca todos os apartamentos
        List<Apartamento> apartamentos = apartamentoRepository.findAll();
        model.addAttribute("apartamentos", apartamentos);

        // 3. Busca todos os proprietários (para o <select> no formulário)
        List<Proprietario> proprietarios = proprietarioRepository.findAll();
        model.addAttribute("proprietarios", proprietarios);

        // 4. Envia um objeto "Apartamento" vazio para o formulário de cadastro
        if (!model.containsAttribute("apartamento")) {
            model.addAttribute("apartamento", new Apartamento());
        }

        // 5. Retorna o nome do arquivo HTML
        return "rel_apto";
    }

    /**
     * Ponto 6.2 do Roteiro: Gravar dados novo apartamento
     * Mapeamento: @PostMapping(“cad-apto”)
     * Template Thymeleaf: rel_apto.html (redireciona)
     *
     * Este método lida com a requisição POST de /cad-apto (envio do formulário).
     */
    @PostMapping("/cad-apto")
    public String cadastrarNovoApartamento(
            @Valid @ModelAttribute("apartamento") Apartamento apartamento,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // 6. Verifica se a validação encontrou erros nos campos do apartamento
        // (ex: numeroPorta, quantidadeQuartos, tipoOcupacao)
        if (bindingResult.hasErrors()) {
            // Se houver erros, redireciona de volta exibindo os erros
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.apartamento", bindingResult);
            redirectAttributes.addFlashAttribute("apartamento", apartamento);
            return "redirect:/rel-apto";
        }
        
        // 7. Se não houver erros, salva o novo apartamento
        // O Spring JPA é inteligente o suficiente para saber que o 'proprietario'
        // vindo do formulário (pelo ID) deve ser vinculado.
        apartamentoRepository.save(apartamento);

        // 8. Adiciona uma mensagem de sucesso
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Apartamento cadastrado com sucesso!");

        // 9. Redireciona o navegador para a URL /rel-apto (o método GET acima)
        return "redirect:/rel-apto";
    }
}