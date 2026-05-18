# Dev Container — Apresenta.AI

Esse dev container existe pra **zerar o atrito de setup** entre as 6 pessoas do time. Em vez de cada uma instalar Node, configurar versões, alinhar extensões e regras de lint na mão, basta abrir o repositório com a extensão **Dev Containers** do VS Code (ou no GitHub Codespaces) e o ambiente sobe pronto.

## O que vem dentro do container

- **Node 22** com `pnpm` habilitado via Corepack
- **GitHub CLI** (`gh`)
- Extensões do VS Code pré-instaladas:
  - ESLint, Prettier, Tailwind CSS IntelliSense, shadcn-ui, pretty-ts-errors
- Settings travados:
  - Format on save com Prettier
  - ESLint auto-fix on save
- `pnpm install` rodado automaticamente no primeiro start (`postCreateCommand`)

## Como abrir

**VS Code (local):**

1. Instale a extensão [Dev Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers).
2. Abra o repositório.
3. Cmd/Ctrl + Shift + P → **"Dev Containers: Reopen in Container"**.

**GitHub Codespaces:**

Clique em **Code → Codespaces → Create codespace on \<branch\>**.

## O que funciona dentro do container

- Edição de qualquer arquivo do frontend (TS, TSX, CSS)
- IntelliSense, lint, format, type check
- `pnpm install`, `pnpm lint`, `pnpm typecheck`, `pnpm build`
- `pnpm format`
- Git e `gh` (push, PR, etc.)

## Dois modos de dev: Electron (host) e Web (container)

A app final é **Electron** — é isso que vai ser entregue, empacotado em `.dmg`/`.exe`/`.AppImage`. Mas o Electron abre uma **janela nativa** do sistema, e containers Docker não têm display gráfico. Por isso temos dois modos de desenvolvimento:

### `pnpm dev` — modo Electron (precisa rodar no host)

Sobe o Electron de verdade: main process, preload, renderer com HMR. É o modo que reflete 100% o app final.

Precisa rodar **no host** (sua máquina nativa), porque a janela do Electron tem que ser renderizada pelo seu SO.

```bash
# No host:
pnpm dev
```

### `pnpm dev:web` — modo Web (roda no container)

Sobe **só o renderer React** num servidor Vite, sem Electron. Você abre `localhost:5173` no navegador do host e itera na UI normalmente — mesmo código, mesmo HMR, mesmo Tailwind, mesmas rotas.

Funciona dentro do dev container porque o Vite é HTTP puro (a porta 5173 é forwardada pro host automaticamente). Codespaces também funciona — abre uma URL pública.

```bash
# Dentro do container:
pnpm dev:web
# Depois abra http://localhost:5173 no seu navegador
```

**Limitação:** APIs específicas do Electron (`window.electron`, IPC, `app.getPath`, etc.) não existem no modo web. Pra UI pura (90% do tempo num CRUD com editor), não importa. Quando precisar testar integração com o main process, troque pro modo `pnpm dev` no host.

### Quando usar cada um

| Você está | Use |
|---|---|
| Mexendo em UI, Tailwind, layout, rotas | `pnpm dev:web` (container) |
| Mexendo em chamadas IPC, menus, dialogs nativos | `pnpm dev` (host) |
| Validando lint / types / build antes de PR | qualquer (container ou host) |
| Testando o app "como o usuário vai ver" | `pnpm dev` (host) |

## Alternativa: ambiente totalmente nativo

Se não quiser usar dev container, instale na mão:

```bash
# Pré-requisitos
node --version    # 22.x
corepack enable
pnpm --version    # 10.x

# Setup
pnpm install
pnpm dev          # modo Electron
# ou
pnpm dev:web      # modo web (abrir localhost:5173)
```

Os defaults de Prettier/ESLint/Tailwind virão das configs do repo. Recomendamos instalar as extensões listadas em `.vscode/extensions.json` pra ter a mesma experiência do container.

## Quando o backend chegar

Na Sprint 1 o backend Java vai entrar no monorepo. A intenção é adicionar um **segundo container** ou **features no devcontainer** com Java 25 + Maven, mantendo o mesmo princípio: tudo roda no container, exceto a janela do Electron. Detalhes virão no `backend/README.md`.
