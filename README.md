# Projeto API FitTogether

## Pré-requisitos

- [Docker](https://www.docker.com/get-started) e [Docker Compose](https://docs.docker.com/compose/install/)
- Java 21 ou superior (para compilar o projeto)
- Maven

## Configuração Inicial

### 1. Criar o Arquivo `app.properties`

Crie um arquivo chamado `app.properties` no diretório raiz do projeto. Este arquivo conterá as variáveis de ambiente usadas pela aplicação, como informações de acesso ao banco de dados e configurações de e-mail.

No arquivo `app.properties`, adicione o seguinte conteúdo, substituindo os valores das variáveis conforme necessário:

```properties
# Configurações do banco de dados
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/fittogether
SPRING_DATASOURCE_USERNAME=usuario
SPRING_DATASOURCE_PASSWORD=senha

# Configurações para o envio de e-mail da aplicação
SPRING_MAIL_USERNAME=email
SPRING_MAIL_PASSWORD=senha
```

### 2. Configurar o Docker Compose

No projeto, você encontrará um arquivo `docker-compose.yml` que configura o MySQL como banco de dados para a aplicação.

Certifique-se de que o arquivo `docker-compose.yml` contém o seguinte conteúdo:

```yaml
services:
  db:
    container_name: mysql_local
    image: mysql:8
    volumes:
      - ./db:/var/lib/mysql
    environment:
      MYSQL_ALLOW_EMPTY_PASSWORD: "yes"
      MYSQL_ROOT_PASSWORD: "root"
      MYSQL_DATABASE: "fittogether"
      MYSQL_USER: "fittogether"
      MYSQL_PASSWORD: "fittogether"
      MYSQL_ROOT_HOST: "%"
    ports:
      - "3306:3306"
```

> **Nota:** Pode-se de substituir `MYSQL_ROOT_PASSWORD`, `MYSQL_USER`, e `MYSQL_PASSWORD` por outros valores.

### 3. Subir o Banco de Dados com Docker Compose

No terminal, navegue até o diretório do projeto e execute o seguinte comando para subir o banco de dados MySQL:

```bash
docker-compose up -d
```

Esse comando irá baixar a imagem do MySQL (caso ainda não esteja disponível) e iniciar o container.

### 4. Compilar e Rodar a Aplicação

Após configurar o banco de dados e o arquivo `app.properties`, você pode rodar a aplicação Spring Boot.

A aplicação estará acessível em [http://localhost:8080](http://localhost:8080).

### 5. Acessar o Banco de Dados

Você pode acessar o banco de dados diretamente usando um client MySQL. Use as credenciais que configurou no `docker-compose.yml`:

- **Host**: `localhost`
- **Porta**: `3306`
- **Usuário**: `seu_usuario`
- **Senha**: `sua_senha`

### 6. Documentação

Você pode acessar a documentação em [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)


## Resumo

1. Crie `app.properties` com as variáveis de ambiente.
2. Configure o Docker Compose para o MySQL.
3. Suba o banco com `docker-compose up -d`.
4. Rode a aplicação.
5. Acesse a aplicação em `http://localhost:8080`.
