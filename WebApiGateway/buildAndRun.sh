#!/bin/sh
mvn clean package && docker build -t uees.sistema.distribuido/WebApiGateway .
docker rm -f WebApiGateway || true && docker run -d -p 9080:9080 -p 9443:9443 --name WebApiGateway uees.sistema.distribuido/WebApiGateway