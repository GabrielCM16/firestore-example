# Minicurso de Introdução ao Firebase :D

## Criando um projeto Maven (do zero)
```
mvn archetype:generate -DgroupId=com.exemplo -DartifactId=meu-projeto -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
```mvn```: Este é o comando para invocar o Maven. É necessário que o Maven esteja instalado e configurado no seu sistema.

```archetype:generate```: Este é um objetivo do Maven que indica que você deseja gerar um novo projeto a partir de um archetype. Um archetype é um modelo que contém a estrutura básica de um projeto Maven.

-```DgroupId=com.exemplo```: Esta opção define o groupId do seu projeto. O groupId é um identificador único que geralmente segue a convenção de nome de domínio inverso. No exemplo, com.exemplo é utilizado para identificar o grupo do projeto.

-```DartifactId=meu-projeto```: Esta opção define o artifactId do seu projeto. O artifactId é o nome do projeto que você está criando. Neste caso, o projeto será chamado de meu-projeto.

-```DarchetypeArtifactId=maven-archetype-quickstart```: Esta opção especifica qual archetype usar para gerar o projeto. O maven-archetype-quickstart é um archetype padrão que cria um projeto Java simples, com a estrutura básica necessária, incluindo diretórios para código-fonte e testes.

-```DinteractiveMode=false```: Esta opção desativa o modo interativo, o que significa que o Maven não irá solicitar entrada do usuário durante a execução do comando. Ele usará os parâmetros fornecidos diretamente no comando para criar o projeto, o que é útil para automatização ou quando você não deseja responder a perguntas durante o processo.


## Compilando
```
mvn compile
```

## Instalar Dependencias
```
mvn install
```

## Executar o projeto
```
mvn exec:java -Dexec.mainClass="com.exemplo.Main"
```


## Faltando o arquivo JSON, adminsdk com as credenciais!
Deve-se gerar um arquivo de adminsdk (JSON com as credenciais) no console do firebase e colocar do diretorio corretamente.
Também mudar o ```setDatabaseUrl``` do arquivo ```Firebase.java``` e ajustar o nome do arquivo caso necessario.
```
/firestore-example
  ├── src
  │   └── main
  │     └── java
  │         └── com
  │             └── example
  │                 └── Firebase.java
  │     └── resources
  │         └── arquivo adminsdk
  ├── pom.xml
  ├── README.md
  └── .gitignore
```