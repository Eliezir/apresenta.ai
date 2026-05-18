<div align="center">

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="./assets/banner-dark.png">
  <source media="(prefers-color-scheme: light)" srcset="./assets/banner-light.png">
  <img src="./assets/banner.png" alt="Apresenta.AI" width="820"/>
</picture>

<br/>

### Slides interativos em HTML, gerados por IA a partir do seu Markdown.

<br/>

[![Java](https://img.shields.io/badge/Java-25+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring](https://img.shields.io/badge/Spring-Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![Electron](https://img.shields.io/badge/Electron-47848F?style=for-the-badge&logo=electron&logoColor=white)](https://www.electronjs.org/)
[![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black)](https://react.dev/)
[![SQLite](https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white)](https://www.sqlite.org/)
[![Claude](https://img.shields.io/badge/Claude-D97757?style=for-the-badge&logo=anthropic&logoColor=white)](https://www.anthropic.com/)

<sub>Projeto da disciplina de **Programação Orientada a Objetos** — IFAL</sub>

</div>

<br/>

---

## Sobre

> Aplicação desktop que transforma suas ideias em **apresentações HTML interativas** com a ajuda de IA.

Você começa escrevendo livremente o conteúdo da sua apresentação. A IA converte esse documento em um **roteiro estruturado em Markdown**, que você ajusta conversando com ela até ficar do jeito que quer. No final, com base em um **tema visual** e em **instruções/presets** definidos por você, a IA gera a apresentação final em **HTML real** — com animações, transições, componentes interativos e tudo que a web oferece. Esqueça slides estáticos.

<br/>

## Como funciona

```mermaid
flowchart TD
    A[<b>1 · Documento de conteúdo</b><br/><sub>Usuário escreve livremente o material<br/>da apresentação em texto corrido</sub>]
    A --> B[<b>2 · Roteiro Markdown</b><br/><sub>IA estrutura o conteúdo em<br/>seções, tópicos e transições</sub>]
    B --> C{Refinar com IA?}
    C -->|Sim| D[<sub>Conversar com a IA<br/>para ajustar seções, reescrever<br/>trechos ou gerar mais conteúdo</sub>]
    D --> C
    C -->|Roteiro aprovado| E[<b>3 · Tema visual + presets</b><br/><sub>Cores, tipografia, layout HTML<br/>e instruções customizadas</sub>]
    E --> F[<b>4 · Geração</b><br/><sub>IA produz a apresentação<br/>em HTML interativo</sub>]
    F --> G{Resultado OK?}
    G -->|Trocar tema| E
    G -->|Ajustar conteúdo| C
    G -->|Pronto| H([Apresentação final])

    style A fill:#fef3c7,stroke:#f59e0b,color:#000
    style B fill:#dbeafe,stroke:#3b82f6,color:#000
    style E fill:#fce7f3,stroke:#ec4899,color:#000
    style F fill:#ede9fe,stroke:#8b5cf6,color:#000
    style H fill:#d1fae5,stroke:#10b981,color:#000
```

<br/>

## Exemplos

<div align="center">

<sub>_Apresentações no estilo que o Apresenta.AI gera_</sub>

<br/><br/>

<table>
  <tr>
    <td align="center" width="33%" valign="top">
      <a href="https://eliezir.github.io/IHC-Spotify-Heuristics-Presentation/slides/">
        <img src="./assets/examples/spotify-heuristics.png" width="100%" alt="Heurísticas de Nielsen no Spotify"/>
      </a>
      <br/><br/>
      <b>Heurísticas de Nielsen no Spotify</b>
      <br/>
      <sub>IHC · Avaliação heurística</sub>
      <br/><br/>
      <a href="https://eliezir.github.io/IHC-Spotify-Heuristics-Presentation/slides/">Ver apresentação →</a>
    </td>
    <td align="center" width="33%" valign="top">
      <a href="https://eliezir.github.io/strategy/slides/">
        <img src="./assets/examples/strategy-pattern.png" width="100%" alt="Design Pattern Strategy"/>
      </a>
      <br/><br/>
      <b>Design Pattern: Strategy</b>
      <br/>
      <sub>POO · Padrão de projeto</sub>
      <br/><br/>
      <a href="https://eliezir.github.io/strategy/slides/">Ver apresentação →</a>
    </td>
    <td align="center" width="33%" valign="top">
      <a href="https://eliezir.github.io/percurso-cognitivo-adt-studio/slides/">
        <img src="./assets/examples/percurso-cognitivo.png" width="100%" alt="Percurso Cognitivo ADT Studio"/>
      </a>
      <br/><br/>
      <b>Percurso Cognitivo ADT Studio</b>
      <br/>
      <sub>IHC · Inspeção de usabilidade</sub>
      <br/><br/>
      <a href="https://eliezir.github.io/percurso-cognitivo-adt-studio/slides/">Ver apresentação →</a>
    </td>
  </tr>
</table>

</div>

<br/>

## Funcionalidades

<table>
  <tr>
    <td width="50%" valign="top">
      <h3>Editor Markdown</h3>
      <ul>
        <li>Syntax highlighting via Monaco</li>
        <li>Preview em tempo real</li>
        <li>Auto-save periódico</li>
        <li>Indicação de alterações não salvas</li>
      </ul>
    </td>
    <td width="50%" valign="top">
      <h3>Modelos Visuais</h3>
      <ul>
        <li>Paleta de cores e tipografia customizáveis</li>
        <li>Template HTML base reutilizável</li>
        <li>Preview com conteúdo de exemplo</li>
        <li>CRUD completo de modelos</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td width="50%" valign="top">
      <h3>Templates de Markdown</h3>
      <ul>
        <li>Salvar Markdown como template reutilizável</li>
        <li>Biblioteca pessoal pesquisável</li>
        <li>Usar template ao criar novo projeto</li>
        <li>Edição com preview em tempo real</li>
      </ul>
    </td>
    <td width="50%" valign="top">
      <h3>Provedores de IA</h3>
      <ul>
        <li>Suporte a Claude (Anthropic)</li>
        <li>Provedor Mock para testes</li>
        <li>Cadastro de múltiplas credenciais</li>
        <li>Teste de conexão integrado</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td width="50%" valign="top">
      <h3>Projetos</h3>
      <ul>
        <li>Criar em branco ou a partir de template</li>
        <li>Listar com data de última modificação</li>
        <li>Trocar modelo visual a qualquer momento</li>
        <li>Renomear e remover</li>
      </ul>
    </td>
    <td width="50%" valign="top">
      <h3>Geração e Histórico</h3>
      <ul>
        <li>Geração de slides HTML via IA</li>
        <li>Visualização em tela cheia</li>
        <li>Histórico completo por projeto</li>
        <li>Regerar com modelo diferente</li>
        <li>Comparar versões lado a lado</li>
      </ul>
    </td>
  </tr>
</table>

<br/>

## Stack

<div>

| Backend | Frontend | Integrações |
|:--:|:--:|:--:|
| Java 25+ | Electron | Anthropic API (Claude) |
| Spring Boot | React | Provedor Mock |
| SQLite | Monaco Editor | |
| JUnit 5 | shadcn/ui | |
| Jackson | | |
| BCrypt | | |

</div>

<br/>

## Equipe

<div>

| | Pessoa | GitHub |
|:--:|:--|:--|
| | **Andrezza Abreu** | [@dzzabreu](https://github.com/dzzabreu) |
| | **Carlos Henrique Roque** | [@Roque-if](https://github.com/Roque-if) |
| | **Eduardo Calado** | [@doardoE](https://github.com/doardoE) |
| | **Eliezir Moreira** | [@Eliezir](https://github.com/Eliezir) |
| | **Maria Luísa Alaquoke** | [@quokequack](https://github.com/quokequack) |
| | **Thomas Pinheiro** | [@thomas-pinheiro](https://github.com/thomas-pinheiro) |

<sub>Orientação: **Prof. Fernando Kenji Kamei**</sub>

</div>

<br/>

---

<div align="center">
  <sub>IFAL · Programação Orientada a Objetos · 2026</sub>
</div>
