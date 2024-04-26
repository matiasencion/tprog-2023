}echo "HOLA BIENVENIDO AL STARTUP DEL SERVIDOR DE TRABAJOUY"

killall -9 java

cd ..

ENLACE=$(cat settings.properties)

ENLACE="${ENLACE}?wsdl"

cd ServidorWS/
cd target/
java -Dhsqldb.lock_file=false -cp ./servidor.jar   org.hsqldb.util.DatabaseManagerSwing &
sleep 5


java -jar ./servidor.jar &

sleep 5

cd ../../Deploy/jaxws-ri/bin

chmod +x wsimport.sh

./wsimport.sh -keep "${ENLACE}"

cp -r ./net "../../../ClienteWS/src/main/java/"

cp -r ./servidor "../../../ClienteWS/src/main/java/"

cd ../../../ClienteWS/target/

cp web.war "../../Deploy/Tomcat/webapps/"

cd ../../Deploy/Tomcat/webapps

rm -rf ./web

cd ../bin

chmod +x startup.sh
chmod +x catalina.sh

./startup.sh

cd ../../

echo "ACTUALIZAR catalina.sh CON LA DIRECCION DE JAVA_HOME, PONER LAS VARIABLES DE ENTORNO DE MAVEN EN WINDOWS"
