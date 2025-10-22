# Exercício 1: Edição do Código Fornecido (Loja)

**Objetivos Alcançados:**
1.  Acrescentar imagens de produtos (usando `picsum.photos` como placeholder realista).
2.  Estilizar as imagens para consistência visual.
3.  Criar páginas de destino (`produto1.html`, etc.) para os botões "Comprar".
4.  Corrigir o posicionamento do rodapé para o fim da página (footer stick-to-bottom).

---

## 🖼️ (a) Rascunho/Low-Fi

O rascunho visualizou a organização 3x1 (desktop) / 1x3 (mobile) dos cards e o posicionamento do footer no final da viewport.

## 💡 (b) Decisões Descartadas

| Tentativa Descartada | Razão para Descarte | Decisão Adotada |
| :--- | :--- | :--- |
| Usar `position: fixed` no rodapé. | O `fixed` oculta o rodapé em páginas com muito conteúdo e é ruim para acessibilidade (cobre conteúdo). | Utilizar o padrão `min-height: 100vh`, `display: flex`, `flex-direction: column` no `body` e `flex: 1` no container principal (`.main-content`). Isso garante que o rodapé fique no final, mas role normalmente quando houver conteúdo suficiente. |
| Usar apenas `img-fluid` nas imagens. | As imagens tinham alturas variadas, causando desalinhamento visual na linha de cards, quebrando a consistência do layout. | Criei a classe `.card-img-container` com altura fixa (`height: 200px`) e apliquei `object-fit: cover` na imagem, garantindo que todas as imagens tenham o mesmo tamanho sem distorção. |
| Usar `col-4` fixo. | Para garantir a responsividade em telas menores (celulares), o `col-4` ficaria muito estreito e quebraria o layout. | Adotei `col-sm-6` (2 cards por linha em telas pequenas) e `col-md-4` (3 cards por linha em telas médias e maiores), garantindo um layout responsivo e melhor usabilidade. |