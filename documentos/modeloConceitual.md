
# Aula de Desenvolvimento de Aplicações WEB 
# Desenvolvimento de Aplicações para WEB – 2020/2 (2021)

## 1 - Modelagem de domínio em nível conceitual
- Domínio: área de negócio com seus contextos, regras e especificidades
- Modelo de Domínio: explicitação do domínio em forma de entidades (tabelas, classes ou outros formatos) e as respectivas relações (inter-relações)


### Níveis de Abstração
- Conceitual/Análise :dart: Independente de Tecnologia/Paradigma :dart: Diagrama de Classes :dart: Analista/Arquiteto
- Lógico/Design :dart: Informações do Paradigma :dart: Diagrama de Classes :dart: Projetista
- Físico/Implementação :dart: Detalhes da Tecnologia :dart: Java/SQL :dart: Implementador

### Exemplos:
- Diagrama de Classes Conceitual - sem detalhes da tecnologia
![Modelo Conceitual Gestão de Obras](https://raw.githubusercontent.com/marcoswagner-commits/gestao_obras_aula_daw/Documentos/diagrama_classes_conceitual_2.jpg)


- Diagrama de Classes Lógico - com detalhes do paradigma
![Modelo Conceitual Gestão de Obras](https://raw.githubusercontent.com/marcoswagner-commits/gestao_obras_aula_daw/Documentos/diagrama_classes_conceitual_1.png)

- Diagrama de Entidade-Relacionamento - com detalhes do paradigma
![Modelo Conceitual Gestão de Obras](https://raw.githubusercontent.com/marcoswagner-commits/gestao_obras_aula_daw/Documentos/diagrama_entidade_relacionamento.png)

### Estudo de Caso - Gestão de Obras

- Título: Gestão de Obras de Engenharia Civil
- Objetivo: Gerir todos os gastos de uma obra específica distinguindo os lançamentos por classificação
- Público-alvo: Engenheiros civis, mestres de obra, pedreiros e proprietários de obras
- Mercado: Todos os gerenciadores de obras 
- Descrição: 
O sistema terá como premissa armazenar/gravar o proprietario de uma obra, registrando nome, cpf e e-mail. A obra ou seja uma construção civil que terá uma descrição (por exemplo casa geminada), localização e um complemento. O proprietário pode ter várias obras, no entanto uma obra estará ligada apenas a um proprietário. Para fazer os lançamentos de gastos com a obra - incluindo o valor, descrição, documento, observações - haverá uma classificação em dois níveis (item e subitem) com descrição e complemento. Desta forma será possível, por exemplo, conhecer todos os gastos com mão-de-obra e especificamente com pintura.

### Conceitos Básicos para criação de um Diagrama de Classes UML 
** existem características relacionadas às classes, atributos, métodos e suas relações que não serão abordadas aqui **
- [x] A classe é composta de três partes (nome - atributos - métodos)
  - [ ] Os atributos podem ser privados (private "-"), públicos (public "+") e protegidos (protected "#")
    - [ ] Os atributos representam o comportamento da classe (em um modelo conceitual simples são suficientes, não necessitando dos métodos, para representar uma classe)
  - [ ] Os métodos podem ser privados (private "-") ou públicos (public "+")
    - [ ] Os métodos representam o conjunto de ações que a classe pode realizar

- [x] Relacionamentos entre as classes
  - [ ] Dependência
  - [ ] Associação
  - [ ] Agregação
  - [ ] Composição
  - [ ] Herança (Generalização/Especialização)
  - [ ] Cada uma destas relações podem ter:
    - [ ] Verbos que estabelecem a relação (usa, cadastra, aluga...)
    - [ ] Multiplicidade (aspecto que estabelece na relação de uma classe com outra as possibilidades quantitativas - 1 para 1, 1 para muitos, muitos para muitos)
    - [ ] Navegabilidade (aspecto que estabelece o direcionamento entre as classes)
    - [ ] Símbolos (retas) que representam o tipo de relacionamento conforme a figura abaixo:

![Relacionamentos](https://raw.githubusercontent.com/marcoswagner-commits/gestao_obras_aula_daw/Documentos/relações_classes.png)

Um artigo que apresenta com detalhes os conceitos de Diagrama de Classes UML pode ser acessado em:
https://www.devmedia.com.br/orientacoes-basicas-na-elaboracao-de-um-diagrama-de-classes/37224
