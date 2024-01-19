# Teste de Avaliação de Alunos

Este projeto implementa um sistema de avaliação de alunos com foco em diferentes tipos de testes: americano, verdadeiro/falso e de escolha múltipla. O sistema permite a entrada de dados dos testes, o processamento das respostas dos alunos e a geração de pontuações individuais, além de calcular a média geral.

## Estrutura do Projeto

O projeto é composto por quatro classes principais:

### 1. **Client**

A classe `Client` contém o método principal que executa os testes e imprime as pontuações dos alunos. Utiliza instâncias das classes de teste para processar e avaliar as respostas dos alunos.

### 2. **AmericanTest**

A classe `AmericanTest` implementa a interface `Test` e oferece suporte ao processamento de testes no estilo americano, que incluem perguntas de múltipla escolha e abertas. Os dados do teste são lidos e gravados em um arquivo, e as pontuações dos alunos são calculadas com base nas respostas.

### 3. **TrueOrFalseTest**

A classe `TrueOrFalseTest` também implementa a interface `Test` e lida com testes de verdadeiro ou falso. Os dados do teste são armazenados em um arquivo, e as respostas dos alunos são comparadas com as respostas corretas para calcular as pontuações.

### 4. **MultipleQuestionsTest**

A classe `MultipleQuestionsTest` implementa a interface `Test` para processar testes de escolha múltipla. Os dados do teste são lidos e gravados, e as pontuações dos alunos são calculadas com base nas respostas fornecidas.

### 5. **Test Interface**

A interface `Test` define métodos comuns para processar e avaliar qualquer tipo de teste, além de recuperar as pontuações dos alunos.

## Como Usar

1. Execute o método `main` na classe `Client` para iniciar a execução do programa.
2. Siga as instruções para fornecer os dados do teste.
3. As pontuações dos alunos serão calculadas e exibidas no final da execução.

## Observações Importantes

- Os dados do teste são armazenados em um arquivo chamado "data/data.txt".
- Certifique-se de seguir as instruções fornecidas durante a execução do programa para inserir os dados corretamente.

Este projeto foi desenvolvido utilizando Java e oferece uma solução simples para avaliação de alunos em diferentes tipos de testes. Sinta-se à vontade para adaptar e expandir conforme necessário.