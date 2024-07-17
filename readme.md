# Forum API
## Sobre o Projeto
Este projeto foi desenvolvido para um desafio da Alura, como terceiro desafio da turma 6 do Oracle Next Education. O objetivo é fornecer uma API para criação, atualização, exibição e exclusão de tópicos de discussão.
## Tecnologias Utilizadas
- Java: Linguagem principal usada para o desenvolvimento do projeto.
- Spring Boot: Framework usado para criar aplicações independentes e de produção baseadas em Spring.
- Spring Data JPA: Para acesso a dados com Java Persistence API.
- Spring Security: Para garantir a segurança da aplicação.
- Flyway: Usado para migração de esquema de banco de dados.
- MySQL: Banco de dados utilizado para armazenar dados.
- JWT (JSON Web Tokens): Usado para autenticação e autorização.
- Lombok: Para reduzir código repetitivo.
##Endpoints Principais
### Autenticação
- POST /login
+ Descrição: Realiza o login do usuário e retorna um token JWT.
+ Corpo da requisição:
```
{
   "username": "string",
"password": "string"
}
```
+ Resposta:
```
{
   "token": "string"
   }
   ```
### Tópicos
- GET /topicos
+ Descrição: Retorna todos os tópicos ordenados pela data de criação em ordem decrescente.
+ Parâmetros: pageable (opcional)
+ Resposta: Lista de tópicos com detalhes.
- GET /topicos/{id}
+ Descrição: Retorna detalhes de um tópico específico pelo ID.
+ Parâmetro: id (ID do tópico)
+ Resposta: Detalhes do tópico.
- POST /topicos
+ Descrição: Cria um novo tópico.
+ Corpo da requisição:
   ```
    {
     "title": "string",
       "description": "string",
     "status": "string",
     "author": "string",
     "course": "string"
    }
   ```
+ Resposta: Detalhes do tópico criado.
- PUT /topicos/{id}
+ Descrição: Atualiza um tópico existente pelo ID.
+ Corpo da requisição:
```
{
"title": "string",
"description": "string",
"status": "string"
   }
   ```
+ Parâmetro: id (ID do tópico)
+ Resposta: Detalhes do tópico atualizado.
- DELETE /topicos/{id}
+ Descrição: Deleta um tópico existente pelo ID.
+ Parâmetro: id (ID do tópico).
##Como Executar o Projeto Localmente
1. Pré-requisitos: Certifique-se de ter o JDK instalado na sua máquina e um ambiente de desenvolvimento adequado (por exemplo, IntelliJ IDEA ou Eclipse).
2. Clonar o Repositório
   ```
   git clone https://github.com/gabriel-oxx/forum.git
   ```
   ```
   cd forum
   ```
3. Configurar as Variáveis de Ambiente
Copie o arquivo .env.example para .env e ajuste as variáveis de acordo com o seu ambiente local.
4. Compilar.
5. Iniciar o Servidor
   ```
    ./mvnw spring-boot:run
    ```
## Licença
Este projeto é licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.      
