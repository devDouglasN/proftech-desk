# 👨‍💼☎️ ProfTech-Desk Backend     

### ProfTech-Desk é um sistema de Help Desk que permite o cadastro e exclusão de clientes e técnicos gerenciado pelo banco de dados, possibilitando que os clientes registrem chamadas e os técnicos as atendam.   

<br>

⚙️ Funcionalidades     
+ 🧾 Cadastro de clientes   
+ 👷‍♂️ Cadastro de técnicos  
+ 📝 Registro e gerenciamento de chamados  
+ 🔄 Atualização e exclusão de clientes, técnicos e chamados   

<br>

🛠️ Tecnologias Utilizadas  
| Tecnologia          | Versão  |
|---------------------|---------|
| Java                | 17      |
| Spring Boot         | 3.1.0   |
| Spring Security     | 3.1.0   |
| JPA                 | 3.1.0   |
| Maven               | 3.9.6   |
| Auth0 JWT           | 4.2.1   |
| MySQL               | 8.0     |
| Apache HttpClient5  | 5.2.3   |

<br>  

📁 Estrutura do Projeto  
proftechdesk-back/   
├── src/  
│ ├── main/   
│ │ ├── java/com/douglas/proftechdesk/  
│ │ │ ├── config/  
│ │ │ ├── domain/
│ │ │ ├── repositories/  
│ │ │ ├── resources/  
│ │ │ ├── security/  
│ │ │ ├── services/  
│ │ │ └── ProfTechDeskApplication.java  
│ ├── resources/  
│ │ ├── application.properties  
│ │ ├── application-dev.properties  
│ │ ├── application-test.properties  
├── .gitignore  
├── Dockerfile  
├── pom.xml  
├── README.md
  

<br>

## 🚀 Pré-requisitos  
+ Java 17
+ Maven
+ MySQL 

<br> 

## 📥 Instalação   
Clonando o Repositório  
```
git clone git@github.com:devDouglasN/proftech-desk.git
```` 
```
cd proftech-desk
````

<br>

## Instalando as Dependências
```
mvn clean install
````

<br>
 
## 🏃‍♂️ Rodando o Projeto
Ambiente de Desenvolvimento
+ Para iniciar a aplicação, execute:

```
mvn spring-boot:run  
````
A aplicação estará disponível em http://localhost:8080.

<br>

## 🔗 Endpoints  
### Você pode utilizar o Insomnia, Postman ou qualquer outra ferramenta de sua preferência para realizar as requisições.

### Clientes
POST ( Cadastrando um cliente )
+ http://localhost:8080/customers

```
 {
    "name": "Ex: Tite",
    "cpf": "Ex: 046.066.040-34",
    "email": "Ex: tite@mail.com",
    "password": "Ex: 123",
    "profiles": Ex: [0]
}
````

GET ( Retornando lista de clientes )
+ http://localhost:8080/customers

<br>

GET ( Retornando cliente por ID )
+ http://localhost:8080/customers/{ID}
Basta adicionar o ID desejado na requisição.

<br>


PUT (Atualizando clientes)
http://localhost:8080/customers/{id}

<br>

DELETE ( Deletando um cliente ) 
+ http://localhost:8080/customers/{id}   
Basta adicionar o ID desejado na requisição.

<br>

### Técnicos
POST ( Cadastrando um técnicos )
+ http://localhost:8080/technicals

```
 {
    "name": "Ex: Pedro Martins",
    "cpf": "Ex: 451.025.990-54",
    "email": "Ex: pedro@gmail.com",
    "password": "Ex: 123",
    "profiles":Ex: [0],
    "creationDate": "Ex: 30/01/2024"
}
````

<br>

GET ( Retornando lista de técnicos )
+ http://localhost:8080/technicals

<br>

GET (Retornando técnico por ID )
+ http://localhost:8080/technicals/{id}
Basta adicionar o ID desejado na requisição.   

<br>

PUT (Atualizando técnico)
http://localhost:8080/technicals/{id}

<br>

DELETE ( Deletando um técnico ) 
+ http://localhost:8080/technicals/{id}   
Basta adicionar o ID desejado na requisição.


<br>

### Chamados
POST ( Cadastrando um chamado )
+ http://localhost:8080/tickets

```
 {
    ""Ex: priority": 2,
    ""Ex: status": 1,
    ""Ex: title": "Ticket test create",
    ""Ex: observations": "test create",
    ""Ex: technical": 7,
    ""Ex: customer": 13
}
````

<br>

GET ( Retornando lista de chamado )
+ http://localhost:8080/tickets

<br>

GET (Retornando chamado por ID )
+ http://localhost:8080/tickets/{id}
Basta adicionar o ID desejado na requisição.   

<br>

PUT (Atualizando técnicos)
http://localhost:8080/tickets/{id}

<br>

DELETE ( Deletando um chamado ) 
+ http://localhost:8080/tickets/{id}   
Basta adicionar o ID desejado na requisição.

<br>
<br>

Developed by [Douglas do Nascimento](https://github.com/devDouglasN).
