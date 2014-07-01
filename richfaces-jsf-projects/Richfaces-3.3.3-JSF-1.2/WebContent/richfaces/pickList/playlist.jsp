<!doctype html public "-//w3c//dtd html 4.0 transitional//en">


<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!--  copied from Richfaces-demo-3.3.3.Final/richfaces/orderingList/example/playlist.xhtml -->
<!--  http://docs.jboss.org/richfaces/latest_3_3_X/en/devguide/html/rich_orderingList.html -->

<!--
	order list type rich:datalist 
	http://www.w3schools.com/cssref/pr_list-style-type.asp
 -->
 
<html>
<head>
  <style>
	.cent{
		text-align:center;
	}
	.top{
		vertical-align:top;
	}
	.70per{
		width:70%;
	}
	.30per{
		width:200px;
	}
  </style>
</head>
<body>
<f:view>
	<h:form>
		<h:panelGrid columns="2" width="600" style="vertical-align:top; valign:top">
		<rich:orderingList value="#{avMasterMaintenanceHandler.sameClpnameList}" var="rpt" 
				columnClasses="top, top"
				listHeight="200" listWidth="350" 
				orderControlsVisible="false" fastOrderControlsVisible="false"
				converter="reportMasterConverter" 
				selection="#{avMasterMaintenanceHandler.selectedSameClpnameSet}">
			<rich:column  width="180">
			<f:facet name="header">
				<h:outputText value="Program Name" />
			</f:facet> 
				<h:outputText value="#{rpt.title}"></h:outputText>
			</rich:column>
			<rich:column> 
				<f:facet name="header">
					<h:outputText value="Description" />
				</f:facet>
				<h:outputText value="#{rpt.artistName}"></h:outputText><br/>
			</rich:column>
			<a4j:support event="onclick" ignoreDupResponses="true" requestDelay="500" action="#{avMasterMaintenanceHandler.takeSelection}" reRender="output"/>
			<a4j:support event="onkeyup" ignoreDupResponses="true" requestDelay="500" action="#{avMasterMaintenanceHandler.takeSelection}" reRender="output"/>
		</rich:orderingList>

		<rich:panel id="output" header="Selected Item" style="overflow:auto; width:300px; height:200px; vertical-align:top">
			<rich:dataList value="#{avMasterMaintenanceHandler.selectedSameClpnameList}" var="song" 
							rendered="#{not empty avMasterMaintenanceHandler.selectedSameClpnameList}" 
							type="circle">
				<h:outputText value="#{song.title}"></h:outputText>
			</rich:dataList>
			<h:outputText value="No items selected" rendered="#{empty avMasterMaintenanceHandler.selectedSameClpnameList}"/>
		</rich:panel>
		</h:panelGrid>
		
		<p>
		<p>
	<h:panelGrid columns="2" columnClasses="top, top">
		
		<rich:pickList value="#{avMasterMaintenanceHandler.result}"> 
			<f:selectItems value="#{avMasterMaintenanceHandler.capitalsOptions}"/>
			<a4j:support event="onlistchanged" reRender="result"/>
		</rich:pickList>
		
		<rich:panel id="result" bodyClass="pbody">
			<f:facet name="header">
				<h:outputText value="#{avMasterMaintenanceHandler.items} Options Choosen"></h:outputText>
			</f:facet>
			<rich:dataList value="#{avMasterMaintenanceHandler.result}" 
			var="pickList" rendered="#{avMasterMaintenanceHandler.items>0}"> 
					<h:outputText value="#{pickList}"/>
			</rich:dataList> 
		</rich:panel>
	</h:panelGrid>

	<h:panelGrid columns="2" columnClasses="top, top">
		<h:commandButton id="cancel" value="Cancel" action="#{avMasterMaintenanceHandler.cancel }"></h:commandButton>&nbsp;&nbsp;&nbsp;
		<h:commandButton id="save" value="Save" action="#{avMasterMaintenanceHandler.save }"></h:commandButton>
	</h:panelGrid>
		
	</h:form>
</f:view>
</body>
</html>