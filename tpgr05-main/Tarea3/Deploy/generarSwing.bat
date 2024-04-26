echo GENERANDO SWING

cd ../ServidorWS

call mvn clean 

call mvn package install

cd target/

ren ServidorWS-0.0.1-SNAPSHOT-jar-with-dependencies.jar servidor.jar

cd ..

cd ..

cd Deploy

echo SWING GENERADO