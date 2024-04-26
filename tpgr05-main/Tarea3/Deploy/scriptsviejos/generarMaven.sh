#!/bin/bash

echo "HOLA BIENVENIDO AL GENERADOR MAVEN DE TRABAJOUY"

cd ../ServidorWS

mvn clean 

mvn package install

cd target/

mv ServidorWS-0.0.1-SNAPSHOT-jar-with-dependencies.jar servidor.jar

cd ../../ClienteWS

mvn clean 

mvn package install

cd target/

mv trabajouy-0.0.1-SNAPSHOT.war web.war

cd ../../Deploy

echo "ARCHIVOS MAVEN GENERADOS, AHORA PONGA EL LINK DEL WSDL SIN '?WSDL' EN EL settings.properties, LUEGO LEVANTAR EL SERVIDOR"

