package com.professorangoti.torresmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.professorangoti.torresmo.model.Produto;
import com.professorangoti.torresmo.service.ProdutoService;

@Controller
public class ProdutoController {

  @Autowired
  ProdutoService produtoService;

  @GetMapping("/produtos")
  public String exibeProdutos(Model model) {
    List<Produto> produtos = produtoService.findAll();
    System.out.println("Qtde: " + produtos.size());
    for (Produto produto : produtos) {
      System.out.println("Produto: " + produto.getNome());
    }
    model.addAttribute("produtos", produtos);
    return "produtos";
  }

  @GetMapping("/")
  public String home(Model model) {
    List<Produto> produtosEmDestaque = produtoService.findDestaques();
    
    model.addAttribute("produtos", produtosEmDestaque); 
    
    System.out.println("Qtde de Destaques: " + produtosEmDestaque.size());
    
    return "home";
  }
}