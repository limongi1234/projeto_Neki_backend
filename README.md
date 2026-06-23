# projeto_Neki_backend 🛒

**API REST de e-commerce** desenvolvida em **Spring Boot**, como projeto do processo da **Neki**. Fornece o back-end para uma aplicação de loja virtual, com autenticação, validações e documentação interativa.

## ✨ Recursos

- API REST para o domínio de e-commerce (`projetoApiECommerce`)
- Autenticação e autorização com **Spring Security**
- Validação de dados de entrada (**Bean Validation**)
- Envio de e-mails (**Spring Mail**)
- Persistência em **PostgreSQL** via **Spring Data JPA**
- Documentação interativa com **Swagger (Springfox)**

## 🛠️ Tecnologias

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=swagger&logoColor=black)

- **Java + Spring Boot** (Web, Data JPA, Security, Validation, Mail, DevTools)
- **PostgreSQL** — banco de dados
- **Springfox / Swagger** — documentação da API

## 🚀 Como executar

```bash
git clone https://github.com/limongi1234/projeto_Neki_backend.git
cd projeto_Neki_backend

# configure as credenciais do PostgreSQL em application.properties
# (o script SQL inicial está incluído no repositório)

./mvnw spring-boot:run
```

Com a aplicação rodando, a documentação Swagger fica disponível em `http://localhost:8080/swagger-ui.html`.

## 🔗 Front-end

Este back-end é consumido pelo repositório [`projeto_Neki_front`](https://github.com/limongi1234/projeto_Neki_front) (React + Material-UI).
