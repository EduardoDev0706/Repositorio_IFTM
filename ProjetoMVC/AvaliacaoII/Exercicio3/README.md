# Exercício 3: Header Responsivo Básico (Navbar)

**Objetivo Alcançado:**
Criação de uma Navbar responsiva usando classes `navbar`, `navbar-expand-lg`, `navbar-toggler`, `collapse`, `me-auto` e o utilitário `btn`.

---

## 🖼️ (a) Rascunho/Low-Fi

O rascunho visualizou o alinhamento dos links à esquerda (`me-auto`) e o botão "Login" à direita (`d-flex` no `form`).

## 💡 (b) Decisões Descartadas

| Tentativa Descartada | Razão para Descarte | Decisão Adotada |
| :--- | :--- | :--- |
| Usar `navbar-expand-md`. | O requisito era que os links fossem visíveis/expandidos a partir do breakpoint `lg+`. O `md` faria a expansão muito cedo. | Utilizei **`navbar-expand-lg`** exatamente como solicitado para que o colapso ocorra apenas em telas menores que 992px. |