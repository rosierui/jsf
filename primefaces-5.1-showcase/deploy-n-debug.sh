WORKDIR=`pwd`
TOMCAT=/g01/srv/tomcat-7.0.54

TASK_RUNNING=`ps -eaf | grep "sync-source" | grep -v grep | wc -l`
if [ $TASK_RUNNING -gt 0 ]; then
    echo ""
    echo "sync-source is running, shutdown..."
    id=`ps -eaf | grep "sync-source" | grep -v grep | awk '{print $2}'`
    kill $id
else
    echo ""
    echo "sync-source is not running..."
fi;


TASK_RUNNING=`ps -eaf | grep "tomcat" | grep -v grep | wc -l`
if [ $TASK_RUNNING -gt 0 ]; then
    echo ""
    echo "tomcat is running, shutdown..."
    echo ""
    $TOMCAT/bin/shutdown.sh
else
    echo ""
    echo "tomcat is not running..."
    echo ""
fi;

rm $TOMCAT/webapps/showcase-5.1.war
rm -rf $TOMCAT/webapps/showcase-5.1


echo "rebuild and deploy..."
echo
mvn package
cp target/showcase-5.1-SNAPSHOT.war $TOMCAT/webapps/showcase-5.1.war

echo ""
echo "starting up tomcat..."
echo ""
cd $TOMCAT/bin
./catalina.sh jpda start

cd $WORKDIR

echo "cd $WORKDIR"

echo "starting up tomcat source sync"
./sync-source &
echo ""

