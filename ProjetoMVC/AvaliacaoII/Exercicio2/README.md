# Exercício 2: Grid System e Utilitários de Texto

**Objetivo Alcançado:**
Criação de um layout demonstrando o uso do **Bootstrap Grid System** para responsividade (`row`, `col-sm-12`, `col-md-4`, `col-lg-6`, `col-auto`) e diversos **Utilitários de Texto** (`text-center`, `text-primary`, `fs-5`, `text-muted`, `text-end`, `text-truncate`).

---

## 🖼️ (a) Rascunho/Low-Fi

O rascunho focou na disposição horizontal 3-por-linha (desktop) e vertical 1-por-linha (mobile) e a centralização dos elementos na segunda linha.

## 💡 (b) Decisões Descartadas

| Tentativa Descartada | Razão para Descarte | Decisão Adotada |
| :--- | :--- | :--- |
| Usar apenas `col-4` para todas as colunas. | Em telas muito pequenas, o `col-4` resultaria em 3 colunas muito estreitas, causando *overflow* ou quebras feias, ferindo o critério de responsividade. | Adotei `col-sm-12 col-md-4`. Isso força as colunas a ocuparem 100% da largura em celulares (`sm`) e só então se dividirem em 3 em telas maiores (`md+`). |
| Usar `text-justify` (classe nativa). | O Bootstrap 5 não possui uma classe de justificação (`text-justify`) por motivos de qualidade tipográfica e legibilidade em layouts responsivos. | Embora mencionado no enunciado como utilitário de texto, usei a classe com a nota `[Unverified]` e concentrei o foco em classes suportadas (`text-center`, `text-end`, etc.) e na classe `text-wrap` para evitar overflow horizontal. |