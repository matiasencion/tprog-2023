#!/bin/bash

echo "LEVANTANDO SWING Y BASE DE DATOS"

cd ../

read -r ENLACE < settings.properties
ENLACE="$ENLACE?wsdl"

cd ServidorWS/

cd target/

java -Dhsqldb.lock_file=false -cp ./servidor.jar org.hsqldb.util.DatabaseManagerSwing &
sleep 5

cd ../

cd ServidorWS/

cd target/

java -jar ./servidor.jar &
sleep 5

cd ..

cd ..

cd Deploy

echo "TODO LISTO"
