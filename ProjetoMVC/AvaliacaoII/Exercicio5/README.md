# Exercício 5: Utilitários de Espaçamento e Dimensões

**Objetivos Alcançados:**
1.  Centralização de conteúdo vertical e horizontal na viewport: Uso de `vh-100`, `d-flex`, `justify-content-center`, e `align-items-center`.
2.  Caixa com largura e padding definidos: Uso de `w-50` e `p-4`.
3.  Estilização de parágrafos: Uso de `mb-3`, `text-center`, `bg-light`, `rounded` e `shadow`.

---

## 🖼️ (a) Rascunho/Low-Fi

O rascunho focou na posição central da caixa e na visualização do padding interno e da margem inferior entre os parágrafos.

## 💡 (b) Decisões Descartadas

| Tentativa Descartada | Razão para Descarte | Decisão Adotada |
| :--- | :--- | :--- |
| Usar `mx-auto` para centralizar a caixa. | O `mx-auto` (margin horizontal auto) só funciona para centralizar **blocos** com largura definida. Não funciona para centralizar o conteúdo em ambas as dimensões (vertical/horizontal) na viewport. | Utilizei o conjunto de classes **`d-flex justify-content-center align-items-center`** no pai (`.container-fluid`) para garantir a centralização completa da caixa. |
| Usar classes de padding/margin fixas (ex: `style="padding: 16px"`). | O requisito é utilizar apenas Bootstrap 5. As classes utilitárias de espaçamento (`p-4`, `mb-3`) garantem uma consistência de design system (escala de 0 a 5) e evitam CSS customizado. | Usei as classes **`p-4`** e **`mb-3`** para padding e margem, respectivamente, aderindo à escala de espaçamento do Bootstrap. |