<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<html>
<head>
<title></title>
<style>
<!--
   	/* 	/home/jonathan/project/web/richfaces-3.3.3/samples/richfaces-demo/src/main 
   	 	http://localhost:8080/Richfaces-3.3.3-JSF-2.2/richfaces-demo/contextmenu.jsf
   		/home/jonathan/project/web/richfaces-3.3.3/samples/richfaces-demo/src/main
   	*/

    .cent {
        text-align:center;
    }
    .rich-menu-item {
        text-align:left;
    }
    .rich-menu-group {
        text-align:left;
    }
-->
</style>
</head>
<body>
<f:view>
	
	<figure>
		<h:graphicImage value="/images/weiqi.png" style="border : 5px solid #E4EAEF"/>
    	<figcaption><a href="http://livedemo.exadel.com/richfaces-demo/richfaces/contextMenu.jsf?c=contextMenu">Richfaces ContextMenu</a></figcaption>
	</figure>

 	<a4j:loadScript src="/scripts/picturesUtils.js"/>
    <h:panelGrid columns="1" columnClasses="cent">
		<h:panelGroup id="picture">
		    <h:graphicImage value="/images/flower.png" id="pic" style="border : 5px solid #E4EAEF"/>
		    <rich:contextMenu event="oncontextmenu" attachTo="pic" submitMode="none" disableDefaultMenu="true">
		        <rich:menuItem value="Zoom In" onclick="enlarge('pic');" id="zin"></rich:menuItem>
		        <rich:menuItem value="Zoom Out" onclick="decrease('pic');" id="zout"></rich:menuItem>
		    </rich:contextMenu>
		</h:panelGroup>
    </h:panelGrid>
</f:view>
</body>
</html>
