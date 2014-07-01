<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title></title>
<style>
<!--
   	/* 	code - /home/jonathan/project/web/richfaces-3.3.3/samples/richfaces-demo/src/main 
   	 	url  -  http://localhost:8080/Richfaces-3.3.3-JSF-2.2/richfaces-demo/contextmenu.jsf
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
    .cur{
        cursor: pointer; 
    }
    .top{
        vertical-align: top;
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
    <table  width="400">
        <tbody>
        <tr>
            <td><span onmouseover="updateName('Kate')"  onmouseout="updateName('')">Kate</span></td>
            <td><span onmouseover="updateName('John')"  onmouseout="updateName('')">John</span></td>
            <td><span onmouseover="updateName('Alex')"  onmouseout="updateName('')">Alex</span></td>
        </tr>
        <rich:spacer height="10" />
        <tr>
            <td colspan="3">Name: <b><h:outputText id="showname" value="#{userBean.name}" /></b></td>
        </tr>
        </tbody>
    </table>

    <a4j:form>
        <a4j:jsFunction name="updateName" reRender="showname">
            <a4j:actionparam name="param1" assignTo="#{userBean.name}"  />                  
        </a4j:jsFunction>
    </a4j:form>

</f:view>
</body>
</html>
