echo GENERANDO LA WEB

cd ../ClienteWS

call mvn clean 

call mvn package install

cd target/

ren trabajouy-0.0.1-SNAPSHOT.war web.war

cd ../../Deploy

echo ARCHIVOS MAVEN GENERADOS, AHORA PONGA EL LINK DEL WSDL SIN "?WSDL" EN EL settings.properties, LUEGO LEVANTAR LA WEB