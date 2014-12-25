Install maven libs primefaces-lib.tar.gz first
read lib/readme.txt on how to install

Debug - short cut method
    1) shutdown tomcat, build, deploy, and startup tomcat
         $ cd ~/workspace/primefaces-5.1/showcase
         $ ./deploy-n-debug.sh

    2) eclipse
         Run >> Debug Configurations... >> Remote Java Application >> showcase-5.1

    3) http://localhost:8080/showcase-5.1/

Run showcase-5.1 - Method I
    1) build and deploy showcase-5.1
         $ cd ~/workspace/primefaces-5.1/showcase
         $ ./deploy.sh

    2) start tomcat
         /g01/srv/tomcat-7.0.54/bin/startup.sh

    3) check at tomcat webapps folder (optional)
         $ cd /g01/srv/tomcat-7.0.54/webapps
         $ ls -l
    
    4) http://localhost:8080/showcase-5.1/

Run showcase-5.1 - Method II
    1) start tomcat
         /g01/srv/tomcat-7.0.54/bin/startup.sh

    2) build and deploy showcase-5.1
         $ cd ~/workspace/primefaces-5.1/showcase
         $ mvn tomcat7:deploy

    3) check at tomcat webapps folder (optional)
         $ cd /g01/srv/tomcat-7.0.54/webapps
         $ ls -l
    
    4) http://localhost:8080/showcase-5.1/

Debug showcase-5.1 (10/30/14) - Method I
    1) build and deploy showcase-5.1
         $ cd ~/workspace/primefaces-5.1/showcase
         $ ./deploy.sh

    2) start tomcat in debug mode             # JPDA_ADDRESS="6006"
         /g01/srv/tomcat-7.0.54/bin/debug.sh

         where /g01/srv/tomcat-7.0.54/bin/debug.sh has the following contents: 
           ./catalina.sh jpda start

    3) eclipse
         Run >> Debug Configurations... >> Remote Java Application >> showcase-5.1
        
    4) http://localhost:8080/showcase-5.1/

Debug showcase-5.1 (10/30/14) - Method II
    1) start tomcat in debug mode             # JPDA_ADDRESS="6006"
         /g01/srv/tomcat-7.0.54/bin/debug.sh

         where /g01/srv/tomcat-7.0.54/bin/debug.sh has the following contents: 
           ./catalina.sh jpda start

    2) build and deploy showcase-5.1
         $ cd ~/workspace/primefaces-5.1/showcase
         $ mvn tomcat7:deploy

    3) eclipse
         Run >> Debug Configurations... >> Remote Java Application >> showcase-5.1
        
    4) http://localhost:8080/showcase-5.1/
