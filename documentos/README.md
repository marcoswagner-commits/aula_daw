# Aula de Desenvolvimento de Aplicações WEB 
# Roteiro – Desenvolvimento de Aplicações para WEB – 2022/1

## 1 - Apresentação da disciplina: Plano de Ensino – Conteúdos – Metodologia – Avaliações
	* Vide Plano de Ensino; Planejamento da Disciplina; documento Conteúdos

- [Conteúdo do Curso](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/Conteúdo_Aula_DSW_Módulo_I.pdf)

### 1.1 Conteúdos

1.1.1. API 

1.1.2. REST 

1.1.3. Protocolo HTTP

1.1.4. Ecossistema Spring

1.1.5. O padrão MVC

1.1.6. ORM

1.1.7. Camadas 

1.1.8. Segurança

1.1.9. Consumo de APIs

1.1.10. Frontend

1.1.11. React

1.1.12. Aplicação e repositórios na nuvem

### 1.2 Metodologia

1.2.1. Conceito vs Prática vs Conceito

1.2.2. Implementação sequencial e incremental

### 1.3 Avaliações

1.3.1. Um único projeto (incluindo modelo conceitual; backend fase 1 e 2; frontend e integração)


## 2  - Ferramentas e recursos

### Ferramentas:
Java – https://www.oracle.com/technetwork/java/javase/downloads 

Maven  - https://maven.apache.org 

STS - Spring Tool Suit (Eclipse / Maven / Tomcat / Jackson / JPA): https://spring.io/tools

MySQL (Workbench) – https://dev.mysql.com/downloads/workbench

Postman – https://www.getpostaman.com

Git (gitdesktop) – https://git-scm.com/downloads

Visual Studio Code - https://code.visualstudio.com/download 

Outras instalações necessárias: node, npm, yarn e outros...

### Contas:
Github – http://www.github.com 

Heroku – www.heroku.com 

Netlify – http://www.netlify.com 

Draw.IO - https://app.diagrams.net 

### Recursos:
- Stack OverFlow - https://stackoverflow.com

- Developer Survey - https://insights.stackoverflow.com/survey/2021#overview

- Ecossistema Spring - https://spring.io

- Alura - https://wwww.alura.com.br

- Baeldung - https://www.baeldung.com

## 3 - Modelo Conceitual:
Descrição: O sistema (mini sistema com poucas classes) terá como premissa permitir que seja cadastrado históricos relacionados a obras de engenharia civil. Tendo resumidamente o seguinte contexto: Um proprietário possui uma obra que possui uma descrição e localização. Para esta obra vários lançamentos são efetuados, separando estes fluxos em dois níveis de históricos. O objetivo do sistema é permitir depois de vários lançamentos conhecer o gasto com obra de acordo com cada um dos tipos de lançamento.

![Modelo Conceitual Gestão de Obras](https://raw.githubusercontent.com/marcoswagner-commits/gestao_obras_aula_daw/documentos/documentos/GESTAO_OBRAS.png)

## 4 – Síntese do Curso
Passos:
1. Instalar ferramentas (backend)

2. Criar o modelo conceitual no Draw.io usando um Diagrama de Classes ou Entidade-Relacionamento.

3. Verificar o funcionamento do SGBD (MySQL - Workbench)

4. Criar projeto no GitHub (aula_daw_nome_aluno)

5. Criar projeto no STS4 (Spring Starter Project - Seguir check-list próprio)

6. Fazer o primeiro commit

7. Implementar o Backend (Spring Starter Project - Seguir check-list próprio)

8. Instalar ferramentas (frontend)

9. Implementar o Frontend (React - Seguir check-list próprio)


## 5 – Síntese do Backend
Passos:
1. Instalar o STS, Postman e MySQL Workbench

2. Definir dependências iniciais (Spring Web; Spring Data JPA; SpringBoot DevTools; MySQL Driver)

3. Construir o primeiro “controller” com o primeiro “endpoint” apenas para testar o container TomCat

4. Configurar o acesso ao banco de dados – usuário e senha

5. Criar o DataBase no banco

6. Configurar o arquivo application.properties

7. Criar os modelos (Anotações de entidade) - com as regras de negócio específicas

8. Criar os repositórios (Interfaces – JpaRepository)

9. Criar os serviços (Anotações de Service) - com as regras de negócio específicas

10. Criar os respectivos “controllers” com todos os “endpoints” necessários e com o CRUD necessário

11. Criar e fazer adequação para criação de DTOs

12. Adequar para o nível de maturidade 3 (HATEOAS) com os respectivos links

13. Adequar para uso do Swagger

14. Criar funcionalidades para upload de arquivos e envio de e-mails

15. Criar funcionalidades de segurança (autenticação e autorização) com Spring Security e Token JWT

16. Testar API e disponibiliza-la no Heroku


## 6 – Síntese do FrontEnd
Passos:
1. Instalando o Node.JS, Visual Studio Code, Yarn, React CLI, 

2. Criar o Client React 

3. Criar exemplos de uso de propriedades e estado

4. Ajustes de CSS

5. Configurar as rotas

6. Criar um CRUD com BootStrap

7. Validação de campos

8. Usando o LocalStorage

9. Integração com uma API usando AXIOS

![Integração Backend-Frontend](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/9a7bed6a8fe82e8ebec60d89c270580c00584ea8/documentos/back-front.png)



### Trabalhos
- [Trabalho1 - Modelo Conceitual](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/documentos/documentos/Trabalho1%20-%20DAW.pdf)
- [Trabalho2 - BackEnd Fase 1](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/blob/67b0b65e99e64c0752acd8a4a4f000cb3e244fb6/documentos/Trabalho2%20-%20DAW.pdf)

### Aulas
#### - << JUNHO >>
- Roteiro específico da Aula Inaugural  - 06/06/2023 * (Recepção Alunado) *
- Roteiro específico da Aula Introdutória  - 13/06/2023 * (Apresentação do Planejamento - Plano de Ensino - Metodologias) 
- [Roteiro específico da Aula02 - 13/06/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula02.md)
- [Roteiro específico da Aula03 - 20/06/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula03.md)
- [Roteiro específico da Aula04 - 20/06/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula04.md)
- Roteiro específico da Aula de Apresentação do Modelo Conceitual  - 27/06/2023 *

-  //////////////////////////// INÍCIO DO BACK-END ///////////////////////////////////////////////////////
- [Roteiro específico da Aula05 - 27/06/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula05.md)
#### - << JULHO >>
- [Roteiro específico da Aula06 - 04/07/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula06.md)
- [Roteiro específico da Aula07 - 04/07/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula07.md)
- [Roteiro específico da Aula08 - 11/07/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula08.md)
- [Roteiro específico da Aula09 - 11/07/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula09.md)
#### - << AGOSTO >>
- [Roteiro específico da Aula10 - 01/08/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula10.md)
- [Roteiro específico da Aula11 - 01/08/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula11.md)
- [Roteiro específico da Aula12 - 08/08/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula12.md)
- [Roteiro específico da Aula13 - 08/08/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula13.md)
- [Roteiro específico da Aula14 - 22/08/2023](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula14.md)
-

- << OUTUBRO >>
- Roteiro específico da Aula de Apresentação de Trabalho  - 04/12/2022 * (Avaliação do Backend - fase 1) *
- [Roteiro específico da Aula15 - 05/10/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula15.md)
- [Roteiro específico da Aula16 - 11/10/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula16.md)
- [Roteiro específico da Aula - 18/10/2022](aula17.md) - [CONEPE](https://conepe.ufj.edu.br)
- [Roteiro específico da Aula - 19/10/2022](aula18.md) - [CONEPE](https://conepe.ufj.edu.br)
- [Roteiro específico da Aula17 - 25/10/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula19.md)
- [Roteiro específico da Aula18 - 26/10/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula20.md)

- << NOVEMBRO >>
- [Roteiro específico da Aula21 - 01/11/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula21.md)
- Roteiro específico da Aula de Apresentação de Trabalho  - 08/11/2022 * (Avaliação do Backend - fase 2) *
- //////////////////////////// INÍCIO DO FRONT-END ///////////////////////////////////////////////////////
- [Roteiro específico da Aula22 - 09/11/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula22.md)
- [Roteiro específico da Aula23 - 16/11/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula23.md)
- [Roteiro específico da Aula24 - 22/11/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula24.md)
- [Roteiro específico da Aula25 - 23/11/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula25.md)
- [Roteiro específico da Aula26 - 29/11/2022](https://github.com/marcoswagner-commits/gestao_obras_aula_daw/tree/documentos/documentos/aula26.md)

- << DEZEMBRO >>
- Roteiro específico da Aula de Apresentação de Trabalho  - 06/12/2022 * (Avaliação do FrontEnd com Integração) *
- Roteiro específico da Aula de Apresentação de Trabalho  - 07/12/2022 * (Avaliação do FrontEnd com Integração) *



