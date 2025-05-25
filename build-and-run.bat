@echo off
REM Build the project
call mvn clean install

REM Run the built jar (nao roda)
java -jar ./target/divide-ai-backend-0.0.1-SNAPSHOT.jar
