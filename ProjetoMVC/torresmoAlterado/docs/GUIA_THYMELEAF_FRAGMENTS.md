# 📚 Guia Completo: Thymeleaf Fragments

## 🎯 O que são Fragments?

Fragments são pedaços reutilizáveis de código HTML que podem ser incluídos em várias páginas. É como ter componentes que você pode usar em diferentes lugares do seu site.

## 🏗️ Estrutura do Projeto

```
templates/
├── layout.html              # Template base (master page)
├── produtos.html            # Página que usa o layout
├── exemplo-fragments.html   # Exemplos de uso
└── fragments/
    ├── navbar.html          # Menu de navegação
    ├── footer.html          # Rodapé
    └── card-produto.html    # Cards e alertas reutilizáveis
```

## 📖 Conceitos Principais

### 1. Template Base (Layout)

O `layout.html` é o template principal que define a estrutura comum:

```html
<!DOCTYPE html>
<html th:fragment="layout(title, content)" lang="pt-br" xmlns:th="http://www.thymeleaf.org">
  <head>
    <title th:text="${title}">Título Padrão</title>
    <!-- CSS e meta tags comuns -->
  </head>
  <body>
    <header th:replace="~{fragments/navbar :: navbar}"></header>
    <main class="container">
      <div th:replace="${content}"></div>
    </main>
    <footer th:replace="~{fragments/footer :: footer}"></footer>
  </body>
</html>
```

**Características:**

- `th:fragment="layout(title, content)"` - Define um fragment com parâmetros
- `th:replace="~{fragments/navbar :: navbar}"` - Inclui o navbar
- `th:replace="${content}"` - Onde o conteúdo da página será inserido

### 2. Páginas que Usam o Layout

Cada página substitui todo o HTML pelo layout e passa seu conteúdo:

```html
<!DOCTYPE html>
<html th:replace="~{layout :: layout(content, title)}">
  <head>
    <title>Minha Página</title>
  </head>
  <body>
    <div th:fragment="content">
      <!-- Conteúdo específico desta página -->
      <h1>Bem-vindo!</h1>
    </div>
  </body>
</html>
```

**Como funciona:**

1. `th:replace="~{layout :: layout(content, title)}"` substitui a página inteira pelo layout
2. Passa o fragment `content` como primeiro parâmetro
3. Passa o `title` como segundo parâmetro
4. O Thymeleaf renderiza tudo junto

### 3. Fragments Simples (sem parâmetros)

**navbar.html:**

```html
<nav th:fragment="navbar" class="navbar">
  <a href="/">Home</a>
  <a href="/produtos">Produtos</a>
</nav>
```

**footer.html:**

```html
<footer th:fragment="footer">
  <p>&copy; 2025 Minha Empresa</p>
</footer>
```

**Como usar:**

```html
<header th:replace="~{fragments/navbar :: navbar}"></header>
<footer th:replace="~{fragments/footer :: footer}"></footer>
```

### 4. Fragments com Parâmetros

**Definindo:**

```html
<div th:fragment="cardProduto(nome, descricao, preco, imagem)">
  <div class="card">
    <img th:src="${imagem}" th:alt="${nome}" />
    <h5 th:text="${nome}">Nome</h5>
    <p th:text="${descricao}">Descrição</p>
    <span th:text="${preco}">Preço</span>
  </div>
</div>
```

**Usando:**

```html
<div
  th:replace="~{fragments/card-produto :: cardProduto(
  'Torresmo Tradicional',
  'Crocante e saboroso',
  15.90,
  '/img/torresmo.jpg'
)}"></div>
```

## 🔧 Atributos do Thymeleaf

### th:replace vs th:insert vs th:include

| Atributo     | Comportamento                                         |
| ------------ | ----------------------------------------------------- |
| `th:replace` | Substitui completamente o elemento host pelo fragment |
| `th:insert`  | Insere o fragment dentro do elemento host             |
| `th:include` | Insere apenas o conteúdo do fragment (deprecated)     |

**Exemplo:**

```html
<!-- Fragment -->
<div th:fragment="mensagem">
  <p>Olá!</p>
</div>

<!-- th:replace -->
<div th:replace="~{fragment :: mensagem}"></div>
<!-- Resultado: <div><p>Olá!</p></div> -->

<!-- th:insert -->
<div th:insert="~{fragment :: mensagem}"></div>
<!-- Resultado: <div><div><p>Olá!</p></div></div> -->
```

## 💡 Casos de Uso Comuns

### 1. Alertas Reutilizáveis

```html
<!-- Fragment -->
<div th:fragment="alerta(tipo, mensagem)" th:class="|alert alert-${tipo}|">
  <span th:text="${mensagem}"></span>
</div>

<!-- Uso -->
<div th:replace="~{fragments/alerta :: alerta('success', 'Salvo com sucesso!')}"></div>
<div th:replace="~{fragments/alerta :: alerta('danger', 'Erro ao salvar!')}"></div>
```

### 2. Breadcrumbs

```html
<!-- Fragment -->
<nav th:fragment="breadcrumb(items)">
  <ol class="breadcrumb">
    <li th:each="item : ${items}" th:class="${itemStat.last} ? 'active' : ''">
      <a th:if="${!itemStat.last}" th:href="${item.url}" th:text="${item.label}"></a>
      <span th:if="${itemStat.last}" th:text="${item.label}"></span>
    </li>
  </ol>
</nav>
```

### 3. Paginação

```html
<div th:fragment="paginacao(paginaAtual, totalPaginas)">
  <nav>
    <ul class="pagination">
      <li
        th:each="i : ${#numbers.sequence(1, totalPaginas)}"
        th:class="${i == paginaAtual} ? 'active' : ''">
        <a th:href="@{/produtos(page=${i})}" th:text="${i}"></a>
      </li>
    </ul>
  </nav>
</div>
```

### 4. Modal Reutilizável

```html
<div th:fragment="modal(id, titulo, conteudo)" th:id="${id}" class="modal">
  <div class="modal-dialog">
    <div class="modal-header">
      <h5 th:text="${titulo}">Título</h5>
    </div>
    <div class="modal-body" th:replace="${conteudo}"></div>
  </div>
</div>
```

## 🎨 Boas Práticas

### ✅ Faça:

1. **Organize fragments em diretórios**

   ```
   fragments/
   ├── layout/
   │   ├── navbar.html
   │   └── footer.html
   ├── components/
   │   ├── cards.html
   │   └── forms.html
   └── utils/
       └── alerts.html
   ```

2. **Use nomes descritivos para parâmetros**

   ```html
   <!-- Bom -->
   <div th:fragment="cardProduto(nomeProduto, descricaoProduto, precoProduto)">
     <!-- Ruim -->
     <div th:fragment="cardProduto(n, d, p)"></div>
   </div>
   ```

3. **Documente seus fragments**

   ```html
   <!--
     Fragment: cardProduto
     Descrição: Renderiza um card de produto com imagem, nome e preço
     Parâmetros:
       - nome (String): Nome do produto
       - preco (Double): Preço do produto
       - imagem (String): URL da imagem
   -->
   <div th:fragment="cardProduto(nome, preco, imagem)"></div>
   ```

4. **Use valores padrão quando apropriado**
   ```html
   <div th:fragment="botao(texto, estilo)">
     <button th:class="${estilo} ?: 'btn-primary'" th:text="${texto}">Botão</button>
   </div>
   ```

### ❌ Evite:

1. **Fragments muito grandes** - Divida em menores
2. **Muitos parâmetros** - Considere usar objetos
3. **Lógica complexa em fragments** - Faça no controller
4. **Duplicação de código** - Crie fragments reutilizáveis

## 🚀 Exemplo Completo: Sistema de Produtos

### Controller

```java
@Controller
public class ProdutoController {

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        model.addAttribute("titulo", "Nossos Produtos");
        return "produtos";
    }
}
```

### Template (produtos.html)

```html
<!DOCTYPE html>
<html th:replace="~{layout :: layout(content, titulo)}">
  <head>
    <title>Produtos</title>
  </head>
  <body>
    <div th:fragment="content">
      <h1 th:text="${titulo}">Produtos</h1>

      <div th:if="${#lists.isEmpty(produtos)}">
        <div
          th:replace="~{fragments/card-produto :: secaoVazia(
        '📦', 'Nenhum produto', 'Ainda não temos produtos cadastrados'
      )}"></div>
      </div>

      <div th:unless="${#lists.isEmpty(produtos)}" class="row">
        <div
          th:each="produto : ${produtos}"
          th:replace="~{fragments/card-produto :: cardProduto(
             ${produto.nome},
             ${produto.descricao},
             ${produto.preco},
             ${produto.imagemUrl}
           )}"></div>
      </div>
    </div>
  </body>
</html>
```

## 🔗 Sintaxe de Referência de Fragments

```html
<!-- Sintaxe completa -->
~{templateName :: fragmentName} ~{templateName :: fragmentName(parametro1, parametro2)}

<!-- Exemplos -->
~{layout :: layout(content, title)} ~{fragments/navbar :: navbar} ~{fragments/card-produto ::
cardProduto('Nome', 10.50)}

<!-- Referência ao template completo -->
~{templateName}

<!-- Fragment no mesmo arquivo -->
~{:: fragmentName}
```

## 📝 Resumo

| Conceito                    | Descrição                        | Sintaxe                                 |
| --------------------------- | -------------------------------- | --------------------------------------- |
| **Definir fragment**        | Cria um fragment reutilizável    | `th:fragment="nome"`                    |
| **Fragment com parâmetros** | Fragment que aceita valores      | `th:fragment="nome(p1, p2)"`            |
| **Usar fragment**           | Inclui um fragment               | `th:replace="~{file :: fragment}"`      |
| **Layout master**           | Template base para todas páginas | `th:fragment="layout(content)"`         |
| **Incluir no layout**       | Página usa o layout              | `th:replace="~{layout :: layout(...)}"` |

## 🎓 Recursos Adicionais

- [Documentação Oficial Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#template-layout)
- [Thymeleaf Layout Dialect](https://github.com/ultraq/thymeleaf-layout-dialect)
- [Spring Boot + Thymeleaf Guide](https://spring.io/guides/gs/serving-web-content/)

---

✨ **Dica:** Comece simples com navbar e footer, depois crie fragments mais complexos conforme necessário!
