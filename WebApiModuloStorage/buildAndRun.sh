#!/bin/sh
mvn clean package && docker build -t uees.sistema.distribuido/WebApiModuloStorage .
docker rm -f WebApiModuloStorage || true && docker run -d -p 9080:9080 -p 9443:9443 --name WebApiModuloStorage uees.sistema.distribuido/WebApiModuloStorage