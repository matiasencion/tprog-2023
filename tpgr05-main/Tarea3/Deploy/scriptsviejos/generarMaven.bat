echo HOLA BIENVENIDO AL GENERADOR MAVEN DE TRABAJOUY

cd ../ServidorWS

call mvn clean 

call mvn package install

cd target/

ren ServidorWS-0.0.1-SNAPSHOT-jar-with-dependencies.jar servidor.jar

cd ../../ClienteWS

call mvn clean 

call mvn package install

cd target/

ren trabajouy-0.0.1-SNAPSHOT.war web.war

cd ../../Deploy

echo ARCHIVOS MAVEN GENERADOS, AHORA PONGA EL LINK DEL WSDL SIN "?WSDL" EN EL settings.properties, LUEGO LEVANTAR EL SERVIDOR