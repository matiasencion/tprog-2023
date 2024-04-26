#!/bin/bash

echo "GENERANDO LA WEB"

cd ../ClienteWS

mvn clean
mvn package install

cd target/

mv trabajouy-0.0.1-SNAPSHOT.war web.war

cd ../../Deploy

echo "ARCHIVOS MAVEN GENERADOS, AHORA PONGA EL LINK DEL WSDL SIN '?WSDL' EN EL settings.properties, LUEGO LEVANTAR LA WEB"
