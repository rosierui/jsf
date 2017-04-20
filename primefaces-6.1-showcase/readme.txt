$ git remote show origin
  URL: https://github.com/primefaces/showcase

Primefaces-6.1 showcase feature explore project 

This is a project derived from original primefaces-6.1 showcase
to explore primefaces 6.1 features 

Debug
    1) shutdown tomcat, build, deploy, and startup tomcat
         $ cd /path-to/primefaces-6.1-showcase
         $ ./deploy-n-debug.sh

    2) eclipse
         Run >> Debug Configurations... >> Remote Java Application >> showcase-6.1
         JPDA default debug port: 8000

    3) http://localhost:8080/showcase-6.1/
       http://localhost:8080/showcase-6.1/ui/data/datatable/filter.xhtml
       http://localhost:8080/showcase-6.1/ui/data/datatable/lazy.xhtml
       
