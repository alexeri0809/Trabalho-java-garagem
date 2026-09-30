# Trabalho-java-garagem

First, we need to enter the demo folder using the following command:

cd .\demo\

Then, if you want to test the program, we use the following commands:

Remove-Item -Recurse -Force target\classes

javac -d target\classes src\main\java\com\example\*.java

java -cp target\classes com.example.Main

If you want to test the program, use:

mvn compile

mvn test

mvn compile exec:java "-Dexec.mainClass=com.example.Main"

