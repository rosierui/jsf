<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<html>
     <head>
           <title>RichFaces 3.3.2 - JSF 1.2</title>
     </head>      
     <body>
           <f:view>
           <br/>
			http://localhost:8080/Richfaces-3.3.3-JSF-1.2/componentcontrol.jsf<br/>
			http://docs.jboss.org/richfaces/latest_3_3_X/en/devguide/html/rich_toolTip.html<br/>
           <br/>
           <br/>
           <br/>
           <br/>
			<h:form>
		            <br/>
		            <br/>
					<h:commandLink value="Simple Link" id="link">
					    <rich:toolTip id="tip1" followMouse="true" direction="top-right" mode="ajax" value="#{bean.toolTipContent}" horizontalOffset="5" 
					        verticalOffset="5" layout="block">
					        <f:facet name="defaultContent">
					            <f:verbatim>DEFAULT TOOLTIP CONTENT</f:verbatim>
					        </f:facet>
					    </rich:toolTip>
					</h:commandLink>
		            <br/>
		            <br/>

		            <br/>
		            <br/>			      			
			</h:form>
			
			
			<br/>
			<br/>
			<br/>
			<br/>
			
			
			<br/>
			<br/>
			
			
						
           </f:view>
     </body>
</html>
