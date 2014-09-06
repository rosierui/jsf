Last update: 07/25/14

See license at https://code.google.com/p/primefaces/ (http://www.apache.org/licenses/LICENSE-2.0)

The following folders contains modified pom.xml files to build primefaces 5.1-SNAPSHOT
Currently primefaces 5.1-SNAPSHOT code locate under

https://code.google.com/p/primefaces/source/browse/

read-only checkout:
svn co http://primefaces.googlecode.com/svn/themes/trunk/ themes
svn co http://primefaces.googlecode.com/svn/primeui/trunk/ primeui
svn co http://primefaces.googlecode.com/svn/maven/trunk/ maven
svn co http://primefaces.googlecode.com/svn/primefaces/trunk/ primefaces
svn co http://primefaces.googlecode.com/svn/showcase/trunk/ showcase
svn co http://primefaces.googlecode.com/svn/examples/trunk/ examples

copy primefaces/pom.xml to <above-check-out-folder>/primefaces
copy showcase/pom.xml to <above-check-out-folder>/showcase

build projects according to the above checkout order

where 
    examples/showcase builds prime-showcase-1.0.0-SNAPSHOT.war
    showcase builds showcase-5.1-SNAPSHOT.war


