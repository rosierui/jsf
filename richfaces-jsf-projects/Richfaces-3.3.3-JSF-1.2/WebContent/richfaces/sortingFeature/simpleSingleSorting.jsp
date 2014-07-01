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
 -->

	<style>
		.center{
			text-align:center;
		}
	</style>
</head>
<body>
<f:view>
	<h:form>
		<rich:dataTable value="#{capitalsBean.capitals}" var="cap" width="300px" columnClasses="center"
		rows="15" reRender="ds">
			<f:facet name="header">
				<h:outputText value="Sorting Example"/>
			</f:facet>
			<rich:column sortBy="#{cap.state}"> 
				<f:facet name="header">
					<h:outputText value="State Name"/>
				</f:facet>
				<h:outputText value="#{cap.state}"/>
			</rich:column> 
			<rich:column sortBy="#{cap.name}">
				<f:facet name="header">
					<h:outputText value="State Capital"/>
				</f:facet>
				<h:outputText value="#{cap.name}"/> 
			</rich:column>
			<rich:column>
				<f:facet name="header">
					<h:outputText value="Time Zone"/>
				</f:facet>
				<h:outputText value="#{cap.timeZone}"/> 
			</rich:column>
			<f:facet name="footer">
				<rich:datascroller id="ds"></rich:datascroller>
			</f:facet>
		</rich:dataTable>
	</h:form>
</f:view>
</body>
</html>