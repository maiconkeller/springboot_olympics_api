# Bem vindo

Bem vindo ao Olympics REST API.


## Informações Gerais

Está API também estará hospedada no [Heroku](https://test-olympics-api.herokuapp.com/swagger-ui.html)                                .


## Especificações

- Java 8
- Spring Framework
- Hiraki
- Swagger
- MySQL


## Database

Crie uma instancia do banco de dados com nome 'olympics' e ao executar o sistema pela primeira serão criadas as tabelas e adicionado um registro de exemplo. 
Caso queira, pode modificar o nome, usuario e senha do banco de dados no arquivo 'application.properties'.

```
spring.datasource.url=jdbc:mysql://localhost:3306/olympics
spring.datasource.username=root
spring.datasource.password=root
```
Também deixei o comentado no pom.xml a dependência do hsqldb, caso prefira, descomente e a depêndencia do mysql (lembre-se tbem de remover informações de conexão do application.properties).

## Download e Execução

Para fazer download do código fonte digite o comando abaixo no prompt de comando:

```
$ git clone https://github.com/maiconkeller/springboot_olympics_api.git
```

Para compilar o código com Maven (3.5.2)

```
$ mvn clean install -DskipTests
```

Para executar utilize o comando abaixo:

```
$ java -jar target/olympics-0.0.1-SNAPSHOT.jar
```

