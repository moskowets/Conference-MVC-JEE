# Conference-MVC-JEE
 Conference MVC JEE

Project created as JEE web application.
Serverlet version is 4.

Requirments:
jdk
Tomcat 9.
MySQL.

To open database model you need to have MySQL Workbench.

To compile and create war file, you need to specify output directory.
pom.xml -> project/build/pluginManagment/plugins/plugin (maven-war-plugin)/configuration/outputDirectory
The best way is to provide there a Tomcat webApp directory. It will deploy automaticly.

To debug application, you need edit catalina.bat (by adding this line):
set "JAVA_OPTS=%JAVA_OPTS% -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,suspend=n,server=y"

Build configuration: Add new configuration -> Remote JVM Debug 
Tomcat settings: edit tomcat-users.xml
Set JAVA_HOME
startup.bat - start Tomcat for Windows