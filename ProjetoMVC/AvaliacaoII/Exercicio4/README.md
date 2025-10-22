# Exercício 4: Cards de Produto com Variações

**Objetivos Alcançados:**
1.  Layout de galeria: 2x2 (mobile) → 3x3 (md+), usando `row-cols-2` e `row-cols-md-3`.
2.  Padronização de imagens: Uso da classe **`ratio ratio-4x3`** para manter proporção e `object-fit-cover` [Inference] para preenchimento.
3.  Alinhamento de alturas: Uso de **`h-100`** no card e **`align-items-stretch`** no `row`.
4.  Consistência visual: Botões empurrados para o final do card-body com `mt-auto`.

---

## 🖼️ (a) Rascunho/Low-Fi

O rascunho enfatizou a necessidade de todos os *cards* terem a mesma altura, independentemente da quantidade de texto.

## 💡 (b) Decisões Descartadas

| Tentativa Descartada | Razão para Descarte | Decisão Adotada |
| :--- | :--- | :--- |
| Usar apenas `h-100` nos cards. | O `h-100` faz o card ocupar 100% da altura do seu pai (`.col`), mas o Bootstrap Flexbox Grid não garante que os `.col` tenham alturas iguais por padrão. | Adicionei **`align-items-stretch`** no elemento `.row` (pai) para forçar as colunas a esticarem, garantindo que o `h-100` funcione corretamente e os cards fiquem alinhados. |