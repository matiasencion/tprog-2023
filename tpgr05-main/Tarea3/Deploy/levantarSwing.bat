echo LEVANTANDO SWING Y BASE DE DATOS

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

cd ..

cd ..

cd Deploy

echo TODO LISTO