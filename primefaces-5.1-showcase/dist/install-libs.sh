#http://repository.primefaces.org/org/primefaces/maven-jsf-plugin/1.3.2/
mvn install:install-file -DgroupId=org.primefaces -DartifactId=maven-jsf-plugin -Dversion=1.3.2 -Dpackaging=jar -Dfile=./maven-jsf-plugin-1.3.2.jar -DgeneratePom=true -DcreateChecksum=true

mvn install:install-file -DgroupId=org.primefaces -DartifactId=primefaces -Dversion=5.1-SNAPSHOT -Dpackaging=jar -Dfile=./primefaces-5.1-SNAPSHOT.jar -DgeneratePom=true -DcreateChecksum=true


#mvn install:install-file -DgroupId=com.jdbmonitor -DartifactId=jdbmonitor-common -Dversion=1.1 -Dpackaging=jar -Dfile=./jdbmonitor-common.jar -DgeneratePom=true -DcreateChecksum=true
#mvn install:install-file -DgroupId=com.jdbmonitor -DartifactId=jdbmonitor-driver -Dversion=1.1 -Dpackaging=jar -Dfile=./jdbmonitor-driver.jar -DgeneratePom=true -DcreateChecksum=true

#mvn install:install-file -DgroupId=com.jamonapi -DartifactId=jamonapi -Dversion=2.73 -Dpackaging=jar -Dfile=./jamon-2.73.jar -DgeneratePom=true -DcreateChecksum=true
