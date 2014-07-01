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

    <h:form id="form">

        <h:panelGrid columns="2" columnClasses="top, top">

	        <rich:dataTable value="#{dataTableScrollerBean.tenRandomCars}" var="car" id="table"
	        				onRowMouseOver="this.style.backgroundColor='#F8F8F8'"
	        				onRowMouseOut="this.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'" rowClasses="cur">
	            <rich:column>
	                <f:facet name="header"><h:outputLabel value="Make" /></f:facet>
	                <h:outputText value="#{car.make}"/>
	            </rich:column>
	            <rich:column>
	                <f:facet name="header"><h:outputLabel value="Model" /></f:facet>
	                <h:outputText value="#{car.model}"/>
	            </rich:column>
	            <rich:column>
	                <f:facet name="header"><h:outputLabel value="Price" /></f:facet>
	                <h:outputText value="#{car.price}"/>
	            </rich:column>
	            <rich:componentControl event="onRowClick" for="menu" operation="show">
	                <f:param value="#{car.model}" name="model"/>
	                <f:param value="#{car.make}" name="car"/>
	            </rich:componentControl>
	        </rich:dataTable>
	    
	        <a4j:outputPanel ajaxRendered="true">
	            <rich:panel>
	                <f:facet name="header"><h:outputLabel value="Last Menu Action" /></f:facet>
	                <h:outputText value="#{ddmenu.current}"></h:outputText>
	            </rich:panel>       
	        </a4j:outputPanel>
        </h:panelGrid>
        
        <rich:contextMenu attached="false" id="menu" submitMode="ajax">
            <rich:menuItem ajaxSingle="true">
                <b>{car} {model}</b> details
                <a4j:actionparam name="det" assignTo="#{ddmenu.current}" value="{car} {model} details"/>
            </rich:menuItem>
            <rich:menuGroup value="Actions">  
                <rich:menuItem ajaxSingle="true">
                    Put <b>{car} {model}</b> To Basket
                    <a4j:actionparam name="bask" assignTo="#{ddmenu.current}" value="Put {car} {model} To Basket"/>
                </rich:menuItem>
                <rich:menuItem value="Read Comments" ajaxSingle="true">
                    <a4j:actionparam name="bask" assignTo="#{ddmenu.current}" value="Read Comments"/>
                </rich:menuItem>                
                <rich:menuItem ajaxSingle="true">
                    Go to <b>{car}</b> site
                    <a4j:actionparam name="bask" assignTo="#{ddmenu.current}" value="Go to {car} site"/>
                </rich:menuItem>
            </rich:menuGroup>
        </rich:contextMenu> 
        
    </h:form>      
    </f:view>
</body>
</html>
