# VRChallenge

VR Challenge - Mini Autorizador

Comentários e estratégias

1) O controller deve somente intermediar as chamadas(requisições) enviadas pelo View e as respostas fornecidas pelo Model. 
Sendo assim, a camada de controller deve ser isolada das regra de negócio da aplicação.

  Como o desenvolvimento de uma aplicação pode envolver um ou mais programadores, pode acontecer alguns problemas durante 
o processo de desenvolvimento onde uma alteração implica em mau funcionamento de outra por exemplo. Dito isso, é sempre 
importante que a aplicação utilize de testes para cobrir todas as possibilidades e assim oferecer uma forma de evitar o mau
funcionamento de processos já validados. Outro ponto positivo de utilizar testes unitários é a possibilidade de encontrar
mais rapidamente o mau funcionamento, lentidão de algum processo devido ao isolamento destes testes.

  Outro ponto interessante seria seguir padrões de arquitetura já utilizados no mercado além de clean-code, design patterns 
entre outras funcionalidades já muito difundidas em cada linguagem de programação e tipo de aplicação.


2) Vários serviços alocados em um mesmo servidor pode causar vários problemas, entre eles, o mais sério seria a possibilidade
de uma parte da aplicação causar um erro e derrubar todas as outras aplicações.

3) Em uma arquitetura de microsserviços o número de componentes independentes pode crescer consideravelmente e este tipo
de infraestrutura pode ficar muito complexo e trazer algumas dificuldades como o monitoramento independente de cada aplicação. 


4) Desenvolvimento da aplicação

Para desenvolvimento desta api foi escolhido o bando de dados MySQL.

Durante o desenvolvimento foi seguido o padrão de arquitetura MVC, separando as operações nas seguintes camadas: 

Controller - camada responsável por receber as requições e apresentar as respostas.
Service - camada responsável por definir e isolar as regras de negócio da aplicação. 
Repository - camada responsável por todas as transações junto ao banco de dados.

Também foi desenvolvido um teste para cada situação apresentada no desafio como forma de testar as requisições 
e os retornos. Nestes testes foi utilizado um banco de dados em memória(H2) JUnit e também o TestRestTemplate.
