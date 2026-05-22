# CLAUDE.md — Guide for Claude agents in this repository

> This file orients any Claude agent (or human) opening this repo. Read it before anything else.

---

## 1. The project

**Apresenta.AI** is a desktop app (Electron + React) that turns free-form content into **interactive HTML presentations** powered by AI (Claude / Anthropic). The user writes freely, the AI structures the content into Markdown, the user refines it via chat, picks a visual model + presets, and the AI generates the final presentation as real HTML (with animations, transitions, and interactive components).

College project for the Object-Oriented Programming course — **IFAL**.

---

## 2. Stack

| Layer | Technologies |
|---|---|
| Frontend | Electron 39, React 19, TanStack Router, TanStack Query, Tailwind v4, shadcn/ui (to be installed), Monaco Editor |
| Backend | Java 25, Spring Boot 4, SQLite, Flyway, jBcrypt |
| AI | Anthropic API (Claude) + Mock provider |
| Build | electron-vite, electron-builder, Maven, pnpm |

Repo layout:
```
/frontend     Electron + React (renderer at src/renderer/src)
/backend      Spring Boot
/assets       Logos and banners (logo.png, logo-dark.png, banner-*.png)
```

---

## 3. Collaboration rule — IMPORTANT

**Every new screen or component requires a design proposal approved by the user BEFORE any UI code is written.**

Mandatory flow for each new screen/component:

1. **Proposal first.** The agent presents the proposed design — structured description (layout, hierarchy, states, copy) and/or a textual wireframe and/or a static HTML mockup. When it makes sense, offer **at least 2 variants** with clear tradeoffs (e.g., sober vs. playful, dense vs. airy).
2. **The user votes / requests adjustments.** Iterate until "approved".
3. **Only then implement.** No new screen/component `.tsx` file is written before approval.

This also applies to meaningful variations of existing components (layout change, significant new visual state, tonal shift). Internal refactors that don't change the visual result don't need a vote.

---

## 4. Screen map

The full map of screens, functionality, and components lives at:

```
~/.claude/plans/lets-make-a-plan-cryptic-tome.md
```

(Plan file kept on the maintainer's machine — whenever in doubt about "which screens exist?" or "which component should I reuse?", consult it first. If the path doesn't exist, ask the user.)

Sitemap snapshot:
- `/` — splash
- `/onboarding/*` — welcome / ai-provider / done (skippable, starts with Mock)
- `/projects` — dashboard
- `/projects/new` — wizard
- `/projects/$id?tab=...` — editor (content, script, theme, preview, history)
- `/library/markdown-templates/*` — Markdown templates CRUD
- `/library/visual-models/*` — visual models CRUD
- `/settings/*` — ai-providers, appearance, about
- `/present/$generationId` — fullscreen presenter (separate Electron window)

---

## 5. Closed decisions

- **Single-user local, no login.** BCrypt is used only to hash AI provider API keys before saving them in SQLite.
- **Onboarding is skippable.** First launch starts with the **Mock** provider; real credential setup can happen later (in Settings) or on the first real generation attempt.
- **Unified Markdown editor.** The project's "Content" tab uses the same `<MonacoMarkdownEditor />` as the "Script" tab — there is no separate WYSIWYG editor.
- **Fullscreen presenter** runs in a dedicated Electron window (no chrome).
- **History comparison:** two iframes side by side for MVP; textual diff is a future evolution.
- **Current phase: frontend-only with mocked data.** Until further notice, no backend changes — no new entities, migrations, or endpoints. Every screen ships against fixtures in `frontend/src/renderer/src/lib/mocks/` (or per-feature `mocks.ts`) so the UI can be built, reviewed, and iterated independently. The mock layer must mirror the contract we intend to ship later (same shapes, same query keys) so swapping to real HTTP later is a one-file change per resource.

---

## 6. Common commands

At the repo root (pnpm workspace):

```bash
pnpm install                # install everything
pnpm dev                    # run Electron frontend with HMR
pnpm dev:web                # run renderer only in the browser (no Electron)
pnpm build                  # typecheck + frontend build
```

Backend (inside `/backend`):

```bash
./mvnw spring-boot:run      # start the API at http://localhost:8080
./mvnw test                 # tests
```

Docker (root): `docker-compose up` (see `docker-compose.yml`).

---

## 7. Code hygiene (applies to frontend and backend)

- **Keep files small.** When a file grows large or starts handling more than one clear responsibility, split it into smaller pieces. A screen made of three obvious sections should be three components, not one 600-line file. Same for backend: long controllers/services should be broken into focused units.
- **No duplication across modules.** Before writing a component, hook, util, or service that looks like something you've seen elsewhere in the codebase, **find it and reuse it**. If two modules need the same thing, extract it to a shared location:
  - Frontend shared UI → `frontend/src/renderer/src/components/` (app-specific) or `components/ui/` (primitives).
  - Frontend shared logic → `frontend/src/renderer/src/lib/` (utils) or `hooks/` (React hooks).
  - Backend shared logic → an existing `BO`/util class, or a new one under the appropriate package. Don't copy logic between controllers/services.
- **Extract when the second use appears, not the first.** One-off code stays inline; the moment a second caller needs it, lift it. Don't preemptively abstract.

---

## 8. Branch and PR conventions

- **Branch names use a type prefix**, kebab-case after the slash:
  - `feature/<short-description>` — new functionality (e.g., `feature/onboarding-welcome-screen`).
  - `fix/<short-description>` — bug fixes (e.g., `fix/api-key-input-mask`).
  - `chore/<short-description>` — tooling, config, deps, docs without behavior change (e.g., `chore/install-shadcn-primitives`).
  - `refactor/<short-description>` — internal restructure, no behavior change.
  - `docs/<short-description>` — documentation-only changes.
  - `test/<short-description>` — adding/fixing tests only.
- **One PR = one clear goal.** Keep PRs small *and* coherent — a foundations PR or a full screen flow can be larger when grouping makes review easier than splitting. Rule of thumb: a reviewer should be able to hold the whole PR in their head. The Java POO backend is the academic focus, so the frontend can move faster and group related work into chunky PRs when it makes sense.
- **Main stays working.** A half-built screen behind a hidden route or feature flag is fine; a broken screen on the home page is not.
- **Title mirrors the branch type:** `feat: ...`, `fix: ...`, `chore: ...`, `refactor: ...`, `docs: ...`, `test: ...`.
- **Definition of Done per PR:** screen/feature reachable via its route, uses design tokens (no hardcoded colors), works in light and dark mode, typecheck passes, no unrelated diffs.
- **Never add Claude (or any AI) as a co-author on commits or PRs.** Commits carry the human author only — no `Co-Authored-By: Claude …` trailer, no "Generated with Claude Code" footer in PR bodies, no mention of the assistant in commit messages.

---

## 9. Frontend conventions

- **Routes** in `frontend/src/renderer/src/router.tsx` (TanStack Router).
- **Design tokens** in `frontend/src/renderer/src/assets/main.css` — use the existing OKLch variables; **never** hardcode colors.
- **Dark theme** uses Tailwind v4's `@custom-variant dark (&:is(.dark *))` registered in `main.css`. Toggle by adding/removing the `.dark` class on `<html>` (a `<ThemeProvider />` will own this).
- **Server state** via TanStack Query (`frontend/src/renderer/src/lib/query-client.ts`).
- **Global client state** not yet defined — decide on the first PR that needs it (suggestion: lightweight Zustand).
- **Logos** live in `/assets` (repo root) — import via copy or move to `frontend/src/renderer/src/assets/` when a screen needs them.
- **App-specific components** go in `frontend/src/renderer/src/components/` (to be created). shadcn primitives in `frontend/src/renderer/src/components/ui/`.

---

## 10. Backend conventions

- Base package: `com.dev.apresenta_ia`.
- Layers: `Controller` → `BO` (business logic) → `DAO` (Spring Data JPA) → `Models/Entity`. Immutable DTOs in `Models/VO` (records).
- Errors handled by `Exception/GlobalExceptionHandler`. Throw `ExcecaoNaoEncontrado`, `ExcecaoValidacao`, `ExcecaoApp` as appropriate.
- Schema via **Flyway** at `src/main/resources/db/migration/V{N}__description.sql`. `ddl-auto=validate` — do not rely on auto-create.
- CORS already open for `http://localhost:*` (Electron).
- Endpoints live under `/api/**`.

> **Note:** while the current phase ships frontend-only with mocks (see section 5), no backend code should be touched. The conventions above are kept here for when the backend phase resumes.

---

## 11. Team

Andrezza Abreu, Carlos Henrique Roque, Eduardo Calado, Eliezir Moreira, Maria Luísa Alaquoke, Thomas Pinheiro. Advisor: Prof. Fernando Kenji Kamei.
