# DivideAi - Backend

## Setup
Necessário:
- Java 17
- Maven
- Docker
```
docker build -t divide-ai-backend .
docker run -p 80:80 divide-ai-backend
```
(inicia servidor na porta 80)

# Endpoints
Todos os seguintes endpoints devem ser utilizados com os parâmetros especificados contidos dentro de um JSON.

Utilizar
``` 
Content-Type: application/json
```

## /login (POST)
- email
- password

Exemplo (requisição):
```
{
  "email": "tester-user@mail.com",
  "password": "teste01"
}
```

## /logout (POST)
- (nenhum parametro necessario)

## /register (POST)
- email
- username
- password

Cria novo usuário

Exemplo (requisição):
```
{
  "username": "testerUser",
  "email": "tester-user@mail.com",
  "password": "teste01"
}
```
## /purchase (POST)
- url

Cria compra a partir da URL da nota\
Esse é o endpoint que chama o web scrapper e extrai as informações da nota

Exemplo (requisição):
```
{
    "url": "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?p=43250587042875000487650650003912921204111272|2|1|1|E56497233975F1D6DC81996EBE57D87073F1A04A"
}
```
## /purchase (GET)
- (nenhum parâmetro necessário)
    
Retornas todas as compras do usuário logado

Exemplo (resposta):
```
[
    {
        "id": 3902,
        "items": [
        {
            "id": 52,
            "value": 13.90,
            "payers": [],
            "name": "SOBRECOXA SADIA CORTES 1KG",
            "quantity": 1.00,
            "unit": " UN"
        },
        {
            "id": 53,
            "value": 5.49,
            "payers": [],
            "name": "CERVEJA ORIGINAL 473ML",
            "quantity": 1.00,
            "unit": " UN"
        },
        {
            "id": 54,
            "value": 3.99,
            "payers": [],
            "name": "COOKIES BAUDUCCO 60G",
            "quantity": 1.00,
            "unit": " UN"
        },
        {
            "id": 55,
            "value": 4.79,
            "payers": [
            "Andriel",
            "João",
            "Chiquinho"
            ],
            "name": "ARROZ BERLEZE 1KG",
            "quantity": 1.00,
            "unit": " UN"
        }
        ],
        "payers": [
        "Andriel",
        "João",
        "Chiquinho"
        ],
        "url": "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?p=43250587042875000487650650003912921204111272|2|1|1|E56497233975F1D6DC81996EBE57D87073F1A04A",
        "scanDate": "2025-05-24T21:37:02.746+00:00",
        "purchaseDate": "2025-05-21T16:40:25.000+00:00",
        "storeName": "SUPERMERCADO COPETTI LTDA - VALANDRO",
        "totalValue": 27.17
    },
    {
        "id": 3903,
        "items": [
        {
            "id": 56,
            "value": 13.90,
            "payers": [],
            "name": "SOBRECOXA SADIA CORTES 1KG",
            "quantity": 1.00,
            "unit": " UN"
        },
        {
            "id": 57,
            "value": 5.49,
            "payers": [],
            "name": "CERVEJA ORIGINAL 473ML",
            "quantity": 1.00,
            "unit": " UN"
        },
        {
            "id": 58,
            "value": 3.99,
            "payers": [],
            "name": "COOKIES BAUDUCCO 60G",
            "quantity": 1.00,
            "unit": " UN"
        },
        {
            "id": 59,
            "value": 4.79,
            "payers": [],
            "name": "ARROZ BERLEZE 1KG",
            "quantity": 1.00,
            "unit": " UN"
        }
        ],
        "payers": [],
        "url": "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?p=43250587042875000487650650003912921204111272|2|1|1|E56497233975F1D6DC81996EBE57D87073F1A04A",
        "scanDate": "2025-05-24T21:37:20.953+00:00",
        "purchaseDate": "2025-05-21T16:40:25.000+00:00",
        "storeName": "SUPERMERCADO COPETTI LTDA - VALANDRO",
        "totalValue": 27.17
    }
]
```
## /purchase?id=\<id> (GET)
- (nenhum parâmetro necessário além do id na URL)

Retorna uma compra específica

Exemplo (resposta):
```
{
    "id": 3902,
    "items": [
        {
        "id": 52,
        "value": 13.90,
        "payers": [],
        "name": "SOBRECOXA SADIA CORTES 1KG",
        "quantity": 1.00,
        "unit": " UN"
        },
        {
        "id": 53,
        "value": 5.49,
        "payers": [],
        "name": "CERVEJA ORIGINAL 473ML",
        "quantity": 1.00,
        "unit": " UN"
        },
        {
        "id": 54,
        "value": 3.99,
        "payers": [],
        "name": "COOKIES BAUDUCCO 60G",
        "quantity": 1.00,
        "unit": " UN"
        },
        {
        "id": 55,
        "value": 4.79,
        "payers": [
            "Andriel",
            "João",
            "Chiquinho"
        ],
        "name": "ARROZ BERLEZE 1KG",
        "quantity": 1.00,
        "unit": " UN"
        }
    ],
    "payers": [
        "Andriel",
        "João",
        "Chiquinho"
    ],
    "url": "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?p=43250587042875000487650650003912921204111272|2|1|1|E56497233975F1D6DC81996EBE57D87073F1A04A",
    "scanDate": "2025-05-24T21:37:02.746+00:00",
    "purchaseDate": "2025-05-21T16:40:25.000+00:00",
    "storeName": "SUPERMERCADO COPETTI LTDA - VALANDRO",
    "totalValue": 27.17
}
```

## /purchase (DELETE)
- id

Deleta a compra especificada

## /purchase (PUT)
- id
- payers

Define os pagadores da compra

Exemplo (requisição):
```
{
    "id": 3902,
    "payers": [
        "Andriel",
        "João",
        "Chiquinho"
    ]
}
```

## /item (PUT)
- id
- payers

Define os pagadores do item

Exemplo (requisição):
```
{
    "id": 55,
    "payers": [
        "João",
        "Chiquinho"
    ]
}
```

## /item (POST)
- id

Divide um item de quantidade N em N itens de quantidade 1

Exemplo (requisição):
```
{
    "id": 55,
}
```

## /user (POST)
- email
- username
- password

Altera usuário logado (usa username para identificar o usuário, não para alterar).

Exemplo (requisição):
```
{
    "username": "novoUsuario",
    "email": "novo-usuario@mail.com",
    "password": "senhaSegura123"
}
```

## /user?id=\<id> (GET)
- id (na url)

Retorna os dados do usuário logado.

Exemplo (resposta):
```
{
    "username": "novoUsuario",
    "email": "novo-usuario@mail.com",
}
```

Consultar os arquivos contidos em /http-clients para exemplos