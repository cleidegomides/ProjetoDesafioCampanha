# ProjetoDesafioCampanha

#FERRAMENTAS: Na elabaoração da API foram utilizado as seguintes ferramentas: Intellij, Dbeaver, Docker, MySQL e Postman,Cmdr.

#BACK-END: Spring Boot, Spring, Swagger2(RESTful API).

#FUNÇÃO: Desenvoler uma API que possibilita ao usuário criar, pesquisar, atualizar e deletar uma campanha,
que tem um relacionamento com um torcedor, o qual preicsará realizar um cadastro para poder participar da campanha. A relação entre a campanha e o torcedor é feita através do time do coração. 

#DOCUMENTAÇÃO: URL para acesso à Documentação da API: http://localhost:8080/swagger-ui.html

#COMANDOS UTILIZADOS NO CMDER PARA RODAR O MYSQL NO DOCKER:

docker run -p 3306:3306 --name some-mysql -e MYSQL_ROOT_PASSWORD=xxxxx -d mysql:tag;

docker container ls -a;

docker container start some-mysql;

docker container stop some-mysql.
