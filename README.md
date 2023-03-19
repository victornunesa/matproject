# Tabela Verdade Java

**Programa em Java para identificar e calcular fórmulas lógicas assim como construir suas respectivas tabelas verdade.**  

A tabela utiliza um sistema hierárquico de classes para representar um "*token*" dentro de uma fórmula:

![meusvg](https://github.com/itisluiz/TabelaVerdade/blob/master/diagrama.svg)  

Todos os tokens podem ser "avaliados" para obter um valor-verdade de `true` ou `false`, variáveis são avaliadas de acordo com seu valor-verdade anteriormente atribuido, enquanto operações retornam valores-verdade calculados de acordo com a operação à ser executada e nas avaliações recursivas de seus "operandos".

## Exemplo de input e output no programa

**Input de fórmula: `p > (~p + q)`**

**Output esperado:**
```
┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐
│p│q│ │p│→│(│¬│p│∨│q│)│
├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤
│V│V│ │V│V│ │F│V│V│V│ │
├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤
│V│F│ │V│F│ │F│V│F│F│ │
├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤
│F│V│ │F│V│ │V│F│V│V│ │
├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤
│F│F│ │F│V│ │V│F│V│F│ │
└─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘
├────────▲────────────┤
Razão-verdade: 75%, CONTINGENCIA
```
Onde o mostrador abaixo da tabela indica a raiz da fórmula, e também são mostrados a porcentagem de casos em que a fórmula é verdadeira e seu tipo lógico.
