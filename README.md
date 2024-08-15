# [seuJOB API](https://github.com/danluan/seuJobAPI)

API desenvolvida para a plataforma de empregos fictícia seuJOB utilizando Spring Boot e PostgreSQL.

Implementa um CRUD para cada entidade do banco (com exceção de Feedback), seguindo as regras de negócio. Por ele é capaz criar um usuário, autenticar, se cadastrar como Worker e buscar vagas, ou se cadastrar como Company e cadastrar Jobs. Também foi implementada a camada de Freelancer, onde o Worker pode aplicar para realizar Services.

# Modelagem do banco

 Modelo lógico inicial do banco.

<img src="https://github.com/danluan/seuJobAPI/blob/main/docs/model/Diagrama%20ER%20-%20SeuJOB.png?raw=true" style="width:70%">

# Endpoints da API

| Método | Endpoint                             | Descrição                        |
|--------|--------------------------------------|----------------------------------|
| **POST**   | `{{base_url}}/api/auth`              | Autenticação de usuário          |
| **POST**   | `{{base_url}}/api/user/getUser`      | Obter informações do usuário     |
| **GET**    | `{{base_url}}/api/user/1`            | Obter usuário por ID             |
| **GET**    | `{{base_url}}/api/user`              | Listar todos os usuários         |
| **POST**   | `{{base_url}}/api/user`              | Criar um novo usuário            |
| **PUT**    | `{{base_url}}/api/user/1`            | Editar usuário por ID            |
| **DELETE** | `{{base_url}}/api/user/1`            | Deletar usuário por ID           |
| **GET**    | `{{base_url}}/api/worker/2`          | Obter trabalhador por ID         |
| **GET**    | `{{base_url}}/api/worker`            | Listar todos os trabalhadores    |
| **POST**   | `{{base_url}}/api/worker`            | Criar um novo trabalhador        |
| **PUT**    | `{{base_url}}/api/worker/1`          | Editar trabalhador por ID        |
| **DELETE** | `{{base_url}}/api/worker/1`          | Deletar trabalhador por ID       |
| **GET**    | `{{base_url}}/api/company/1`         | Obter empresa por ID             |
| **GET**    | `{{base_url}}/api/company`           | Listar todas as empresas         |
| **POST**   | `{{base_url}}/api/company`           | Criar uma nova empresa           |
| **PUT**    | `{{base_url}}/api/company/1`         | Editar empresa por ID            |
| **DELETE** | `{{base_url}}/api/company/1`         | Deletar empresa por ID           |
| **GET**    | `{{base_url}}/api/freelancer/1`      | Obter freelancer por ID          |
| **GET**    | `{{base_url}}/api/freelancer`        | Listar todos os freelancers      |
| **POST**   | `{{base_url}}/api/freelancer`        | Criar um novo freelancer         |
| **DELETE** | `{{base_url}}/api/freelancer/3`      | Deletar freelancer por ID        |
| **GET**    | `{{base_url}}/api/job`               | Listar todos os empregos         |
| **GET**    | `{{base_url}}/api/job/1`             | Obter emprego por ID             |
| **POST**   | `{{base_url}}/api/job`               | Criar um novo emprego            |
| **PUT**    | `{{base_url}}/api/job/1`             | Editar emprego por ID            |
| **DELETE** | `{{base_url}}/api/job/2`             | Deletar emprego por ID           |
| **GET**    | `{{base_url}}/api/job/applications/1`| Listar aplicações para um emprego|
| **GET**    | `{{base_url}}/api/service`           | Listar todos os serviços         |
| **GET**    | `{{base_url}}/api/service/2`         | Obter serviço por ID             |
| **POST**   | `{{base_url}}/api/service`           | Criar um novo serviço            |
| **PUT**    | `{{base_url}}/api/service/4`         | Editar serviço por ID            |
| **DELETE** | `{{base_url}}/api/service/3`         | Deletar serviço por ID           |
| **GET**    | `{{base_url}}/api/application`       | Listar todas as aplicações       |
| **GET**    | `{{base_url}}/api/application/1`     | Obter aplicação por ID           |
| **POST**   | `{{base_url}}/api/application`       | Criar uma aplicação para emprego |
| **POST**   | `{{base_url}}/api/application`       | Criar uma aplicação para serviço |
| **PUT**    | `{{base_url}}/api/application/3`     | Editar aplicação por ID          |
| **DELETE** | `{{base_url}}/api/application`       | Deletar aplicação por ID         |


# Colaboradores:
- [Breno Ferreira](https://github.com/bren1n)
- [Caio Rebert](https://github.com/caiorebert)
- [Daniel Luan](https://github.com/danluan)
