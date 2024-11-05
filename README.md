# *_Desafio técnico Caju_*

### Nesse desafio foi desenvolvida uma aplicação para validar uma transação de cartão.

A aplicação consiste de um endpoint post com o path __/transaction__. Para executar esse endpoint é necessário utilizar 
um json no body como o do modelo abaixo:

```
{
  "transactionId": "12345",
  "accountId": "123456",
  "amount": 50.00,
  "mcc": "5411",
  "merchant": "UBER EATS"
}

```

A validação de como será utilizado o saldo, será de acordo com o mcc e o merchant (comerciante), sendo que o merchant
possui maior peso nessa desição, por exemplo:

- Se merchant tiver no nome: "food", "eat" ou "restaurant" será classificado como FOOD mesmo que o mcc seja de MEAL ou
outro benefício.
- Se merchant tiver no nome: "market", "rappy" ou "alimento" será classificado como MEAL mesmo que o mcc seja de FOOD ou
  outro benefício.

Obs: Usei esses valores como exemplo no código para validar se o nome do comerciante estava sendo levado em consideração 
na hora de definir o benefício.

### Para acessar o endpoint via swagger, basta após subir a aplicação acessar o endpoint: 
`http://localhost:8080/swagger-ui.html`

Os três primeiros desafios foram resolvidos no código, o quarto desafio se encontra a resposta abaixo:

L4- Questão aberta
```
    Como formas de validar para apenas uma transação seja executada  em determinado momento pode se usar versionamento 
    para as transações, dessa forma quando uma transação for executada ela valida se o campo de versão é o mesmo no 
    banco do que o da transação, se for diferente a transação não é executada. É possível também usar filas para que as
    transações sejam executadas em ordem, embora seja um processo mais lento.
```