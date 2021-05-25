:+2::+3::+4::+5::+6::+7::+8::+9::+10::+11::+12::+13::+14:
# Aula de Desenvolvimento de Aplicações WEB 
# Desenvolvimento de Aplicações para WEB – 2020/2 (2021)

## 1 - Modelagem de domínio em nível conceitual
- Domínio: área de negócio com seus contextos, regras e especificidades
- Modelo de Domínio: explicitação do domínio em forma de entidades (tabelas, classes ou outros formatos) e as respectivas relações (inter-relações)


### Níveis de Abstração
- Conceitual/Análise :+2: Independente de Tecnologia/Paradigma //// Diagrama de Classes //// Analista/Arquiteto
- Lógico/Design //// Informações do Paradigma //// Diagrama de Classes //// Projetista
- Físico/Implementação //// Detalhes da Tecnologia //// Java/SQL //// Implementador

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
