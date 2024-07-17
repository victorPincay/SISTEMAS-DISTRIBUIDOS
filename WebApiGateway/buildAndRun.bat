@echo off
call mvn clean package
call docker build -t uees.sistema.distribuido/WebApiGateway .
call docker rm -f WebApiGateway
call docker run -d -p 9080:9080 -p 9443:9443 --name WebApiGateway uees.sistema.distribuido/WebApiGateway