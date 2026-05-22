# Backend do Apresenta IA

Desenvolvida com **Java 25** e **Spring Boot 4.0.6** para o motor de geração de slides com Inteligência Artificial.

---

## 🛠️ Stack Tecnológica

* **Linguagem / Framework:** Java 25 | Spring Boot 4.0.6 (Data JPA, Validation, WebFlux)
* **Banco de Dados local:** SQLite 3.x (Desenvolvimento) | H2 Database (Testes em memória)
* **Migrations:** Flyway
* **Provedor de IA:** Anthropic API (Strategy Pattern: Claude & Mock)
* **Qualidade & Testes:** JUnit 5, Mockito, ArchUnit, Spotless, SpotBugs, PMD, Jacoco

---

## 📋 Pré-requisitos & Setup da IDE

Instale localmente na sua máquina física:
1. **JDK 25** [link download](https://www.oracle.com/java/technologies/downloads/)
2. **Maven 3.9.x** ou superior [link download](https://maven.apache.org/download.cgi)
3. **IntelliJ IDEA** [link download](https://www.jetbrains.com/pt-br/idea/download/)

### Configuração Obrigatória no IntelliJ:
* **Annotation Processing (Lombok):** Vá em `Settings (Ctrl + Alt + S)` ➔ `Build, Execution, Deployment` ➔ `Compiler` ➔ `Annotation Processors` e ative a caixa **"Enable annotation processing"**.
* **SDK do Projeto:** Em `File` ➔ `Project Structure (Ctrl + Alt + Shift + S)` ➔ `Project`, aponte o *Project SDK* para o **Java 25**.

---

## 🔐 Configuração do Ambiente (.env)

O projeto lê variáveis confidenciais de um arquivo local. Copie o arquivo de exemplo na pasta `backend/` e preencha seus dados locais:

```bash
cp .env.example .env
```


> **Atenção:** O arquivo `.env` contém credenciais privadas e está listado no `.gitignore`. Nunca altere nem commite esse arquivo.

---

## 💾 Banco de Dados & Migrations

O ecossistema armazena dados localmente em um arquivo SQLite estruturado automaticamente pelo Flyway.

* **Arquivo Local:** `backend/database/apresenta-ia.db` (ignorado pelo Git).
* **Diretório das Migrations:** `src/main/resources/db/migration/`
* **Convenção de Nomes:** `V{N}__{descricao_com_underscores}.sql` (Ex: `V1__criar_tabela_provedores_ia.sql`).

### Regras do Banco:
1. **Imutabilidade:** Nunca modifique um arquivo de migration que já sofreu commit. Se precisar alterar o banco, crie o próximo sequencial (`V2`, `V3`, etc.).
2. **Dados de Teste:** Carga de dados fakes para validação deve ficar isolada em `src/main/resources/db/testdata/` — nunca misturada nas migrations principais.

---

## 🚀 Comandos de Console (Diretamente em `backend/`)

Use o Wrapper do Maven (`./mvnw` ou `mvnw.cmd`) ou o comando global `mvn`:

### 1. Ciclo de Execução
```bash
# Iniciar a API localmente no perfil padrão (dev)
./mvnw spring-boot:run
```

### 2. Esteira de Qualidade Local
Rode estes comandos para validar seu código antes de abrir qualquer Pull Request (PR):

```bash
# Formatar o código e limpar imports fora do padrão automaticamente
./mvnw spotless:apply

# Validar se o código cumpre as regras de formatação do CI
./mvnw spotless:check

# Buscar potenciais bugs lógicos no bytecode compilado
./mvnw spotbugs:check

# Localizar code smells estruturais (baseado no ruleset.xml)
./mvnw pmd:check
```

### 3. Execução de Testes & Cobertura
O projeto usa **Jacoco** com trava de segurança para exigir o mínimo de **70% de cobertura de complexidade ciclomática**.

```bash
# Rodar todos os testes isolados e de integração no banco H2
./mvnw test

# Abrir o relatório de cobertura gerado diretamente no navegador:
start target/site/jacoco/index.html         # Windows
open target/site/jacoco/index.html          # Mac
xdg-open target/site/jacoco/index.html      # Linux
```
*Classes de Modelos (JPA), DTOs, Exceções customizadas e a classe principal de boot (`Application`) estão automaticamente desconsideradas do cálculo de cobertura por não possuírem lógica complexa.*

---

## 🐳 Rodando com Docker

Alternativa ao setup nativo: subir o backend em um container, sem precisar instalar JDK 25 ou Maven na máquina. A SQLite vive em um volume nomeado (`backend-db`), então o banco persiste entre `up`/`down`.

### Pré-requisitos
* Docker Desktop (ou Docker Engine + Compose v2)

### Comandos (a partir da raiz do repositório)

```bash
# Subir em background (constrói a imagem na primeira vez)
docker compose up -d --build

# Acompanhar os logs
docker compose logs -f backend

# Derrubar o container, mantendo o banco
docker compose down

# Derrubar e apagar o volume do SQLite
docker compose down -v
```

A API fica em `http://localhost:8080`. O frontend continua rodando no host (`pnpm dev` ou `pnpm dev:web`) e aponta para essa porta normalmente — Electron precisa do host para renderizar a janela nativa.

### Observações
* O perfil padrão dentro do container é `prod`. Para usar `dev`, exporte `PROFILE_ACTIVE=dev` antes do `docker compose up`.
* O `backend/.env` é opcional; se existir, é carregado automaticamente pelo Compose.
* A primeira build é lenta (download das imagens base + dependências Maven). Builds subsequentes reaproveitam o cache de dependências enquanto o `pom.xml` não mudar.

---

## 📂 Estrutura de Pacotes

A arquitetura segue o isolamento de domínios organizados de forma limpa:
* `config/`: Setup do CORS (Integração com Electron) e instâncias de clientes HTTP.
* `controller/`: Camada expositora de Endpoints REST (Validações de entrada via DTOs).
* `BO/`: Detentor de regras de negócio isoladas.
* `DAO/`: Interfaces de acesso a dados via Spring Data JPA.
* `Models/Entity/`: Entidades persistentes de banco de dados.
* `Models/VO/`: Objetos de transporte imutáveis divididos em subpastas `request/` e `response/`.
* `ia/`: Implementação do padrão de projeto *Strategy* (`IGeradorSlides`) para alternância dinâmica entre Provedores de IA (Claude e ambiente Mock de homologação).
* `exception/`: Tratamento global de erros com mapeamento direto de Status HTTP.

---

## 🏁 Fluxo Recomendado de Desenvolvimento

Antes de realizar qualquer commit, execute o script em cadeia para garantir aprovação imediata no CI do GitHub:

```bash
# 1. Ajustar formatação automágica
./mvnw spotless:apply

# 2. Compilar, varrer qualidade e rodar os testes em lote
./mvnw spotless:check spotbugs:check pmd:check test

# 3. Se o build retornar sucesso (Green), envie seu código
git add .
git commit -m "tipo(escopo): descrição"
```
