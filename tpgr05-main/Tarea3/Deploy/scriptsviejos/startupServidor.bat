echo HOLA BIENVENIDO AL STARTUP DEL SERVIDOR DE TRABAJOUY

cd ../

set /p ENLACE=<settings.properties

set "ENLACE=%ENLACE%?wsdl"

cd ServidorWS/

cd target/

start java -Dhsqldb.lock_file=false -cp ./servidor.jar   org.hsqldb.util.DatabaseManagerSwing 
timeout /t 5 /nobreak >nul

cd ../

cd ServidorWS/

cd target/

start java -jar ./servidor.jar
timeout /t 5 /nobreak >nul

cd ../../Deploy/jaxws-ri

cd bin

call wsimport -keep ""%ENLACE%""

xcopy .\net "..\..\..\ClienteWS\src\main\java\net" /e /y

xcopy .\servidor "..\..\..\ClienteWS\src\main\java\servidor" /e /y

cd ../../../ClienteWS

cd target/

copy web.war "..\..\Deploy\Tomcat\webapps\"

cd ../../Deploy/Tomcat/webapps

rmdir /s /q .\web

cd ../bin

call startup.bat

cd ../../

echo ACTUALIZAR CATALINA.sh CON LA DIRECCION DE JAVA_HOME, PONER LAS VARIABLES DE ENTORNO DE MAVEN