#!/bin/bash

echo "LEVANTANDO LA WEB"

cd ../

read -r ENLACE < settings.properties
ENLACE="$ENLACE?wsdl"

cd Deploy/jaxws-ri/bin

chmod +x wsimport.sh

./wsimport.sh -keep "$ENLACE"

cp -r net ../../../ClienteWS/src/main/java/
cp -r servidor ../../../ClienteWS/src/main/java/

cd ../../../ClienteWS

cd target/

cp web.war "../../Deploy/Tomcat/webapps/"

cd ../../Deploy/Tomcat/webapps

rm -rf web

cd ../bin

chmod +x startup.sh
chmod +x catalina.sh

./startup.sh

cd ../../

echo "TODO LISTO ENTRA A LA WEB"
