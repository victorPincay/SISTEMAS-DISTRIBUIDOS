@echo off
call mvn clean package
call docker build -t uees.sistema.distribuido/WebApiModuloStorage .
call docker rm -f WebApiModuloStorage
call docker run -d -p 9080:9080 -p 9443:9443 --name WebApiModuloStorage uees.sistema.distribuido/WebApiModuloStorage