# AutoBots - Micro Serviço 🚗

Este projeto foi desenvolvido como parte da disciplina **Desenvolvimento Web III** do curso de **Desenvolvimento de Software Multiplataforma**, com o objetivo de aprimorar o Micro Serviço AutoManager em Java, implementando a **atualização de segurança**, por meio de autenticação e autorização utilizando JSON Web Token (JWT).

<br>

O sistema permite o controle e gerenciamento de:

- 🔐 **Autenticação** de usuários via JWT;
- 👥 Perfis de usuário com **autorizações específicas** (Administrador, Gerente, Vendedor, Cliente);
- 🏢 Cadastro de **Empresas**;
- 🚗 Cadastro de **Veículos**;
- 🧰 Cadastro de **Serviços**;
- 🛒 Cadastro de **Mercadorias**;
- 🛍️ Cadastro de **Vendas**.

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
(Lombok, Spring Web, Spring Security, Spring Boot DevTools, JDBC API, Spring Data JPA, Spring Data JDBC, MySQL Driver e JWT).

#### 📌 Observações
Este projeto foi desenvolvido e testado com Java 17. Versões diferentes podem causar incompatibilidades.

---

### 🔁 Clonando o Repositório

```bash
git clone https://github.com/abeatrizdscoelho/AutoBots.git
  ```

```bash
git checkout ATVIV-AutoBots
```

---

### ⚙️ Executando o Projeto
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

### 🔗 Testando a Aplicação

Você pode testar os endpoints utilizando Postman ou Insomnia.

1. Crie uma Empresa (**POST** `http://localhost:8080/empresa/cadastrar`)
```
{
  "razaoSocial": "AutoBots LTDA",
  "nomeFantasia": "AutoBots Oficina",
  "telefones": [
    {
      "ddd": "11",
      "numero": "987654321"
    },
    {
      "ddd": "11",
      "numero": "40028922"
    }
  ],
  "endereco": {
    "estado": "SP",
    "cidade": "São Paulo",
    "bairro": "Centro",
    "rua": "Rua dos Motores",
    "numero": "123",
    "codigoPostal": "01000-000"
  },
  "cadastro": "2025-11-30T00:00:00.000+00:00"
}
```

2. Registre um Usuário (**POST** `http://localhost:8080/usuario/registrar/1`)
```
{
    "nome": "Administrador Premium",
    "nomeSocial": "Admin Premium",
    "credencial": {
        "nomeUsuario": "admin_premium",
        "senha": "Admin123!"
    },
    "perfis": [
        "ROLE_ADMIN"
    ],
    "telefones": [
        {
            "ddd": "12",
            "numero": "955877665"
        }
    ],
    "endereco": {
        "estado": "SP",
        "cidade": "São Paulo",
        "bairro": "Centro",
        "rua": "Rua dos Desenvolvedores",
        "numero": "101",
        "codigoPostal": "01237-001"
    },
    "documentos": [
        {
            "tipo": "CPF",
            "dataEmissao": "2015-03-12T00:00:00.000+00:00",
            "numero": "12655678900"
        },
        {
            "tipo": "RG",
            "dataEmissao": "2015-03-12T00:00:00.000+00:00",
            "numero": "1239867"
        }
    ],
    "emails": [
        {
            "endereco": "admin_premium@gmail.com"
        }
    ]
}
```

3. Faça o login (**POST** `http://localhost:8080/login`)
```
{
    "nomeUsuario": "admin",
    "senha": "Admin123!"
}
```

4. Teste os demais endpoints:
- Cadastro de Mercadoria (**POST** `http://localhost:8080/mercadoria/cadastrar`)
```
{
  "validade": "2026-05-10T00:00:00.000+00:00",
  "fabricacao": "2025-11-01T00:00:00.000+00:00",
  "cadastro": "2025-11-30T00:00:00.000+00:00",
  "nome": "Óleo de Motor",
  "quantidade": 100,
  "valor": 79.90,
  "descricao": "Lubrificante sintético de alta performance para motores a gasolina e flex."
}
```

- Cadastro de Serviço (**POST** `http://localhost:8080/servico/cadastrar`)
```
{
  "nome": "Troca de Óleo",
  "valor": 150.00,
  "descricao": "Substituição completa do óleo do motor e filtro automotivo."
}
```

- Cadastro de Veículo (**POST** `http://localhost:8080/veiculo/cadastrar`)
```
{
  "tipo": "SEDA",
  "modelo": "Corolla",
  "placa": "ABC1D24"
}

```

- Cadastro de Venda (**POST** `http://localhost:8080/venda/cadastrar`)
```
{
  "cadastro": "2025-11-30T00:00:00.000+00:00",
  "identificacao": "VENDA-001",
  "cliente": {
    "id": 
  },
  "vendedor": {
    "id": 
  },
  "veiculo": {
    "id": 1
  },
  "mercadorias": [
    {
      "id": 1
    }
  ],
  "servicos": [
    {
      "id": 1
    }
  ]
}

```
