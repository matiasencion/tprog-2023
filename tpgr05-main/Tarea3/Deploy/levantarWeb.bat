echo LEVANTANDO LA WEB

cd ../

set /p ENLACE=<settings.properties

set "ENLACE=%ENLACE%?wsdl"

cd Deploy/jaxws-ri

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

echo TODO LISTO ENTRA A LA WEB