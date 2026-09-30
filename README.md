# Sistema Bancário em Java

Aplicação de console desenvolvida em Java para praticar fundamentos de programação orientada a objetos e manipulação de dados em memória.

## Funcionalidades

- Criação de contas bancárias
- Depósitos
- Saques com validação de saldo
- Consulta de saldo
- Gerenciamento de múltiplas contas durante a execução
- Menu interativo pelo terminal

## Tecnologias e conceitos

- Java
- Programação Orientada a Objetos (POO)
- Classes e objetos
- Encapsulamento
- `ArrayList`
- Estruturas condicionais e de repetição
- Entrada de dados com `Scanner`

## Estrutura

```text
src/
└── App.java
```

A classe `ContaBancaria` representa uma conta e concentra as operações sobre saldo. A classe `App` controla o menu, a entrada do usuário e a coleção de contas.

## Como executar

Com o Java instalado:

```bash
javac src/App.java
java -cp src App
```

## Objetivo

Projeto criado para consolidar fundamentos de Java e POO por meio de um problema simples, com regras de negócio como validação de depósitos, saques e saldo disponível.

---

Desenvolvido por [Vinícius Calegari](https://github.com/Vinicius-Calegari).
