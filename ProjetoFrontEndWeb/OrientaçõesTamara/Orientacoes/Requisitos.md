Especificações Técnicas e de Planejamento: Migração de Landing Page para React

1. Estimativa de Tempo Global

    Prazo total: 5 a 7 dias úteis.

    Carga horária estimada: 40 a 56 horas de desenvolvimento focado.

    Premissa: Reutilização direta dos design tokens, paleta de cores (WCAG AAA) e tipografia já estabelecidos no protótipo HTML/CSS.

2. Divisão de Funções e Escopo da Equipe (3 Desenvolvedores)
Desenvolvedor 1: Arquitetura Core e Componentes Dinâmicos

    Responsabilidades:

        Configuração inicial do ambiente de desenvolvimento (Vite + React).

        Centralização dos design tokens do CSS no arquivo global de estilos.

        Componentização do Header (implementação do estado de scroll e toggle do menu mobile).

        Migração da lógica do Carrossel de Cards (conversão dos eventos de pointerdown/pointermove para o ecossistema React).

        Componentização do Accordion do FAQ (controle de estado declarativo para abertura e acessibilidade).

    Prioridade: Alta (Bloqueante para o fluxo de integração).

Desenvolvedor 2: Seções Estruturais e Conteúdo Estático

    Responsabilidades:

        Componentização da Hero Section (tratamento de backgrounds e efeitos de glow).

        Componentização da Product Section (organização da lista de recursos do checklist).

        Componentização da Section Profissional (estruturação do bloco de citação e perfil da arquiteta).

        Isolamento dos textos explicativos e referências normativas em arquivos de configuração JSON para renderização via loops (.map()).

    Prioridade: Média.

Desenvolvedor 3: Formulários, Prova Social e Rodapé

    Responsabilidades:

        Componentização da Section de Feedbacks (renderização dos cards de depoimentos dos clientes).

        Componentização do Footer (estruturação das colunas de links de navegação e redes sociais).

        Componentização do Formulário de Contato (gerenciamento de estados dos inputs via useState).

        Implementação da lógica de submissão do formulário, validação de campos obrigatórios e tratamento visual do estado de sucesso.

    Prioridade: Média.

3. Cronograma de Execução e Prioridades

[Dia 1: Setup] ──> [Dias 2-4: Desenvolvimento Isolado] ──> [Dias 5-6: Integração e Build]

    Fase 1 (Dia 1): Setup do repositório, definição da estrutura de pastas, configuração do arquivo global de estilos e distribuição das tarefas no painel de controle.

    Fase 2 (Dias 2 a 4): Desenvolvimento paralelo dos componentes isolados por cada programador em suas respectivas ramificações (branches).

    Fase 3 (Dias 5 e 6): Fusão do código (merge), resolução de conflitos, aplicação de efeitos de transição ao scroll, testes de responsividade e build de produção.

4. Stack de Bibliotecas Recomendadas

    Ferramenta de Build: Vite (para inicialização e hot-reload otimizados).

    Pacote de Ícones: lucide-react (para substituição dos vetores inline SVG do protótipo por componentes gerenciáveis).

    Animações de Scroll: framer-motion (para substituição da lógica imperativa do IntersectionObserver por declarações nativas do React).

    Gerenciamento de Formulário (Opcional): react-hook-form (caso haja necessidade de validações complexas sem perda de performance por renderizações sucessivas).

5. Principais Desafios Técnicos

    Portabilidade do Carrossel: A lógica atual de arrastar e mover baseia-se em manipulação direta do DOM. Adaptar esse comportamento para o fluxo de renderização do React sem comprometer a taxa de quadros (FPS) em dispositivos móveis exige atenção rigorosa.

    Gerenciamento de Assets: Caminhos relativos de imagem (img/*) do HTML comum precisam ser reestruturados na pasta /public ou importados diretamente via JavaScript para que o build final do Vite processe as URLs corretamente.

    Scroll Lock no Menu Mobile: Garantir que, ao ativar o menu expansível em telas menores, o scroll da página principal seja bloqueado temporariamente, evitando falhas de navegação técnica.

6. Pontos de Melhoria Identificados no Protótipo

    Abstração do Carrossel: Se o prazo for reduzido, recomenda-se substituir o desenvolvimento do carrossel customizado por uma solução robusta como Swiper.js, ou simplificar a interação para navegação exclusiva por setas e pontos (dots), eliminando a complexidade do arrasto manual.

    Estados do Formulário: O protótipo atual apenas oculta o formulário no evento de submit. No React, deve-se adicionar um estado de carregamento (loading) no botão para desativá-lo temporariamente e evitar múltiplos envios acidentais pelo usuário.

    Manutenção de Acessibilidade: As tags estruturais e atributos ARIA do HTML original (aria-expanded, role="tablist") devem ser mapeados estritamente para as propriedades equivalentes dos novos componentes React funcionais.
