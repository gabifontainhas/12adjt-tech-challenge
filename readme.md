# Tech Challenge - Sistema de Gerenciamento de Restaurantes

Fase 1 do Tech Challenge - Turma ADJ12

APIs responsáveis pela gestão de usuários do sistema de gerenciamento de restaurantes, tendo dois perfis principais:
* Customer (Cliente)
* Owner (Dono de Restaurante)

A API permite criar novos usuários, alterar seus dados, exclusão de cadastros, alteração de senha e login na aplicação.

## 🛠 Tecnologias e Ferramentas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 4.0.5
* **Banco de Dados:** PostgreSQL
* **Containerização:** Docker & Docker Compose
* **Documentação:** Swagger (OpenAPI)
* **Testes:** Postman

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* [Docker](https://www.docker.com/get-started) instalado.
* [Git](https://git-scm.com/) instalado.

### Passo a passo

1. Clone o repositório:
git clone https://github.com/gabifontainhas/12adjt-tech-challenge.git
2. Executar mvn clean install para gerar o artefato .jar na target
3. Executar o comando docker build -t tech-challenge:latest .
4. Executar o comando docker compose up
5. A aplicação estará disponível em http://localhost:8080

## 📖 Documentação da API (Swagger)
A documentação interativa com todos os endpoints, parâmetros e esquemas de retorno pode ser acessada localmente enquanto a aplicação estiver rodando:
http://localhost:8080/swagger-ui/index.html

## 🧪 Coleção do Postman para Testes
Para validação dos fluxos, foi disponibilizada uma coleção para o Postman, com cenários de sucesso e erro:


## ✒️ Autora

Gabriela Fontainhas - [LinkedIn](https://www.linkedin.com/in/gabriela-fontainhas-5a8b0935/) - [GitHub](https://github.com/gabifontainhas/)

