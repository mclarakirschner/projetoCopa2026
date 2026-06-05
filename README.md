<<<<<<< HEAD
# projetoCopa
=======
# ⚽ Projeto Copa 2026 API

Projeto desenvolvido em Java com Spring Boot para gerenciamento de times e partidas da Copa 2026.

## 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot
* Maven
* H2 Database
* JPA / Hibernate
* Git e GitHub

---

## 📌 Funcionalidades

### 🟢 CRUD de Times

* Cadastrar times
* Buscar todos os times
* Buscar time por ID
* Atualizar time
* Remover time

### 🟡 CRUD de Partidas

* Cadastrar partidas
* Buscar todas as partidas
* Buscar partida por ID
* Atualizar partida
* Remover partida

---

## 📂 Estrutura do Projeto

```bash
src/main/java/com/fatec/time
│
├── controllers
├── entities
├── repositories
├── services

```

---

## ⚙️ Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/mclarakirschner/projetoCopa2026.git
```

---

### 2. Entrar na pasta do projeto

```bash
cd projetoCopa2026
```

---

### 3. Executar o projeto

No terminal:

```bash
./mvnw spring-boot:run
```

Ou executar pela IDE (VS Code ou IntelliJ).

---

## 🗄️ Banco de Dados H2

O projeto utiliza banco em memória H2.

### Acesso ao console H2

```bash
http://localhost:8080/h2-console
```

### Configurações padrão

```bash
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password:
```

---

## 📡 Endpoints da API

### 🟢 Times

| Método | Endpoint    |
| ------ | ----------- |
| GET    | /times      |
| GET    | /times/{id} |
| POST   | /times      |
| PUT    | /times/{id} |
| DELETE | /times/{id} |

---

### 🟡 Partidas

| Método | Endpoint       |
| ------ | -------------- |
| GET    | /partidas      |
| GET    | /partidas/{id} |
| POST   | /partidas      |
| PUT    | /partidas/{id} |
| DELETE | /partidas/{id} |

---

## 👩‍💻 Desenvolvido por

Maria Clara Kirschner Baz

Projeto acadêmico desenvolvido para prática de API REST com Spring Boot.
>>>>>>> 8dd56af3ed4347ab96973ae35d78cc09564c8b46
