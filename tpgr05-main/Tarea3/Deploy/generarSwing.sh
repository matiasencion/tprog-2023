#!/bin/bash

echo "GENERANDO SWING"

cd ../ServidorWS

mvn clean
mvn package install

cd target/

mv ServidorWS-0.0.1-SNAPSHOT-jar-with-dependencies.jar servidor.jar

cd ..

cd ..

cd Deploy

echo "SWING GENERADO"
