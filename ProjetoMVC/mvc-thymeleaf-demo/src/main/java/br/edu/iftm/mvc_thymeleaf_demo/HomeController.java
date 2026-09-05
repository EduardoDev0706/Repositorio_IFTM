package br.edu.iftm.mvc_thymeleaf_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/verproduto")
  public String exibirProduto(Model modelo) {
    Produto produto = new Produto("Notebook", 5000.99);
    modelo.addAttribute("prod", produto);
    return "exibeproduto";
  }
}
