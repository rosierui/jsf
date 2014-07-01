<!doctype html public "-//w3c//dtd html 4.0 transitional//en">


<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<html>
<head>
<!-- 
http://docs.jboss.org/richfaces/latest_3_3_X/en/tlddoc/rich/column.html

Details of usage
In order to change filter event you should change filterEvent attribute on a column (e.g. filterEvent = "onblur")
In order to get or change current filtering value filterValue attribute is provided. It should be defined with initial filtering value on the page or as value binding to get/change it on server.
This is built-in feature. It uses "startsWith" function to make filtering.
If filterBy attribute is set, a column will render default inputs with default filtering behaviour
In order to change default filtering behaviour another definition is used. See the example shown at the second tab("External Filtering")
 -->

</head>
<body>
<f:view>

	<h:form>
		<rich:dataTable value="#{capitalsBean.capitals}" var="cap" rows="20" reRender="ds" id="simpletable">
			<f:facet name="header">
				<rich:columnGroup>
					<rich:column colspan="2" > 
						<h:outputText value="Filtering Example"/>
					</rich:column>	
					<rich:column breakBefore="true">
						<h:outputText value="State Name"/>
					</rich:column>
					<rich:column>
						<h:outputText value="State Capital"/>
					</rich:column>
				</rich:columnGroup>
			</f:facet>
			<rich:column filterBy="#{cap.state}" filterEvent="onkeyup" >
				<h:outputText value="#{cap.state}"/>
			</rich:column> 
			<rich:column filterBy="#{cap.name}" filterEvent="onkeyup" >
				<h:outputText value="#{cap.name}"/> 
			</rich:column>
			<f:facet name="footer">
				<rich:datascroller id="ds" renderIfSinglePage="true"></rich:datascroller>
			</f:facet>
		</rich:dataTable>
		<a4j:commandButton action="#{capitalsBean.resetFilter}" value="Reset Current Filtering" reRender="simpletable" ajaxSingle="true" limitToList="true"/>
	</h:form>
</head>
</f:view>
</body>
</html>
