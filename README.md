Teste-rest-services
===================
###Pré-requisitos
    - maven
    - java 1.8

###Compilando o projeto
    Bastar usar mvn package*
    depois é só executar o jar que esta na pasta target
    java -jar target/facebook-services-1.0-SNAPSHOT-jar-with-dependencies.jar
    *Por algum motivo os testes apotam erros em alguns dos casos quando eu executo eles usando o maven
    (eles funcionaram com o plugin JUnit do inteliJ), provavelmente não terei tempo de resolver isso,
    caso queira ver os testes rodando é só usar mvn package -DskipTests=false
    mas as seguintes requisições funcionam perfeitamente:
        curl http://localhost:8080/person/
        curl http://localhost:8080/person/?limit=x
        curl -X POST --data facebookId=100007710667474 http://localhost:8080/person/
        curl -X DELETE http://localhost:8080/person/100007710667474/
    De qualquer forma os testes estão em test/java/br/com/desafio/teste/

###Cobertura dos testes
    os testes cobrem as requisições feitas corretamente
    e testa os possiveis erros de requisições ruins (facebookId no formato errado ou que não existe).


