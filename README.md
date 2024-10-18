## Instruções

``` text
O desafio foi feito com Java 17, Spring Boot 3.3.4 e Gradle 8.10.2.

A API está rodando na porta padrão 8080, utilizando o banco H2 e para execução do SQS há um docker-compose
na raiz do projeto, para ser executado localmente. O SQS está executando localmente na porta 9325 e é preciso criar uma fila com nome payment.
```

### H2
``` text 
O banco H2 já está sendo populado com alguns dados de vendedor e os possíveis pagamentos.
Para acessar, acesse o endereço http://localhost:8080/h2-console. Por padrão informe no campo JDBC URL jdbc:h2:mem:test,
no campo User Name informe sa.
```

### OpenAPI
``` text 
Para documentação foi utilizado Swagger OpenAPI, acessando localhost:8080/swagger-ui/index.html. Para a execução da rota PUT - /api/payment poderá executar o seguinte payload abaixo:
```

``` json
{
  "seller_id": "18d272f0-abd7-4343-a70b-0ccc563ea819",
  "payment_items": [
    {
      "payment_id": "4ae809b5-5db5-47ac-8ffc-5ef4e20bbbba",
      "payment_value": 100
    },
    {
      "payment_id": "972583d1-e6c8-4b40-818d-b02cee5c2659",
      "payment_value": 250
    }
  ]
}
```