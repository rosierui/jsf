Primefaces-5.1 showcase feature explore project 

This is a project derived from original primefaces-5.4 showcase
to explore primefaces 5.4 features 

Install maven libs primefaces-lib.tar.gz first
read lib/readme.txt on how to install

Debug
    1) shutdown tomcat, build, deploy, and startup tomcat
         $ cd /path-to/primefaces-5.4
         $ ./deploy-n-debug.sh

    2) eclipse
         Run >> Debug Configurations... >> Remote Java Application >> showcase-5.4
         JPDA default debug port: 8000

    3) http://localhost:8080/showcase-5.4/
       http://localhost:8080/showcase-5.4/ui/data/datatable/filter.xhtml
       http://localhost:8080/showcase-5.4/ui/data/datatable/lazy.xhtml

download from org.promefaces source code
https://github.com/primefaces/showcase
