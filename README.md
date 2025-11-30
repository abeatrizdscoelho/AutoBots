# AutoBots - Micro Serviço 🚗

Este projeto foi desenvolvido como parte da disciplina **Desenvolvimento Web III** do curso de **Desenvolvimento de Software Multiplataforma**, com o objetivo de construir um **Micro Serviço em Java** para gerenciamento de Clientes e seus dados associados, seguindo os **níveis de maturidade do Richardson Maturity Model (RMM)**.

<br>

O sistema permite o controle e gerenciamento de:

- 📇 Cadastro de **Clientes**;
- 🏠 Cadastro de **Endereços**;
- 📞 Cadastro de **Telefones**;
- 🪪 Cadastro de **Documentos**.

Além disso, o projeto busca implementar todos os níveis de maturidade do RMM, garantindo que os endpoints sejam RESTful e que a arquitetura siga boas práticas de micro-serviços.

---

<br>

## 🔧 Tecnologias Utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Postman](https://img.shields.io/badge/postman-%23FF6C37.svg?style=for-the-badge&logo=postman&logoColor=white)  

<br>

## ⬇ Guia de Instalação

Este guia oferece instruções detalhadas sobre como baixar, configurar e executar este projeto em sua máquina local.

### Pré-requisitos

**Eclipse IDE ou VSCode**: IDEs recomendadas para edição do código.

**Java 17**: Linguagem utilizada no desenvolvimento. [Baixe o Java](https://www.oracle.com/br/java/technologies/downloads)

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
git checkout ATVII-AutoBots
```

---

### ⚙️ Executando o Projeto
1. Abra o projeto em sua IDE de preferência.
2. Rode a aplicação no arquivo ```AutomanagerApplication.java```.
3. O micro serviço estará disponível em: ```http://localhost:8080```.

---

### 🔗 Testando a Aplicação

Você pode testar os endpoints utilizando Postman ou Insomnia, sem necessidade de configurar manualmente o banco de dados.

A API oferece recursos para manipulação de:
- Clientes (```/cliente```)
- Endereços (```/endereco```)
- Telefones (```/telefone```)
- Documentos (```/documento```)

---

### 📈 Níveis de Maturidade (RMM)

A aplicação AutoManager está sendo ajustada para contemplar todos os níveis do Richardson Maturity Model, garantindo:

1. Nível 0 – RPC over HTTP: Endpoints simples para execução de ações, já implementados nos CRUDs.
2. Nível 1 – Recursos (Resources): Cada entidade possui seu endpoint dedicado.
3. Nível 2 – HTTP Verbs: Uso correto de métodos HTTP (GET, POST, PUT, DELETE) para operações CRUD.
4. Nível 3 – HATEOAS: Implementação de links relacionados para navegação entre recursos, utilizando RepresentationModel do Spring HATEOAS.
