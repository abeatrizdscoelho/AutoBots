# AutoBots - Micro Serviço 🚗

Este projeto foi desenvolvido como parte da disciplina **Desenvolvimento Web III** do curso de **Desenvolvimento de Software Multiplataforma**, com o objetivo de construir um **Micro Serviço em Java** para gerenciamento de Clientes e seus dados associados.

<br>

O sistema permite o controle e gerenciamento de:

- 📇 Cadastro de **Clientes**;
- 🏠 Cadastro de **Endereços**;
- 📞 Cadastro de **Telefones**;
- 🪪 Cadastro de **Documentos**.

---

<br>

## 🔧 Tecnologias Utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Postman](https://img.shields.io/badge/postman-%23FF6C37.svg?style=for-the-badge&logo=postman&logoColor=white)  

<br>

## ⬇ Guia de Instalação

Este guia oferece instruções detalhadas sobre como baixar, configurar e executar este projeto em sua máquina local.

### Pré-requisitos

**Eclipse IDE ou VSCode**: IDEs recomendadas para edição do código.

**Java 17**: Linguagem utilizada no desenvolvimento. [Baixe o Java](https://www.oracle.com/br/java/technologies/downloads)

**MySQL (caso utilize banco externo)**: [Baixe o MYSQL](https://dev.mysql.com/downloads/installer/)

**Spring Initializr**: Projeto gerado utilizando [Spring Initializr](https://start.spring.io), com as seguintes dependências: <br>
(Lombok, Spring Web, Spring Boot DevTools, JDBC API, Spring Data JPA, Spring Data JDBC e MySQL Driver).

#### 📌 Observações
Este projeto foi desenvolvido e testado com Java 17. Versões diferentes podem causar incompatibilidades.

---

### 🔁 Clonando o Repositório

```bash
git clone https://github.com/abeatrizdscoelho/AutoBots.git
  ```

```bash
git checkout ATVI-AutoBots
```

---

### ⚙️ Configurando e Executando o Projeto
1. Abra o projeto em sua IDE de preferência.
2. Certifique-se de que o **Java 17** está configurado no ambiente.
3. Configure o arquivo ```application.properties``` com suas credenciais do banco de dados, por exemplo:
```bash
# URL de conexão com o banco MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/autobots?
createDatabaseIfNotExist=true
spring.datasource.username=usuario
spring.datasource.password=senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
```bash
# Cria o banco/tabelas automaticamente conforme suas entidades
spring.jpa.hibernate.ddl-auto=update
```
4. Rode a aplicação no arquivo ```AutomanagerApplication.java```.
5. O micro serviço estará disponível em: ```http://localhost:8080```.

---

### 🔗 Acessando a Aplicação

Você pode testar os endpoints utilizando o Postman ou Insomnia.

A API oferece recursos para manipulação de:
- Clientes (```/cliente```)
- Endereços (```/endereco```)
- Telefones (```/telefone```)
- Documentos (```/documento```)
