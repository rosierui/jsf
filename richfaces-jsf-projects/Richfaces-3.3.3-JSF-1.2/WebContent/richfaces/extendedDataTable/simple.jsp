<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>

<!-- URI:  http://localhost:8080/Richfaces-3.3.3-JSF-1.2/richfaces/extendedDataTable/simple.jsf 
	See ARC 130002 - archival saved by com and div
-->

<html>
<head>
</head>
<body>
<f:view>
<h:form id="form">
	<h:panelGrid columns="2" columnClasses="top , top">
		<rich:extendedDataTable
				value="#{extendedTableBean.capitalsDataModel}" var="cap" id="table"
				width="580px" height="400px"
				sortMode="#{extendedTableBean.sortMode}"
				selectionMode="#{extendedTableBean.selectionMode}"
				tableState="#{extendedTableBean.tableState}"
				selection="#{extendedTableBean.selection}">
			<rich:column sortable="false" label="Flag" id="col_1">
				<f:facet name="header">
					<h:outputText value="Flag" id="flag"/>
				</f:facet>
				<h:graphicImage value="#{cap.stateFlag}" id="cap_state_flag"/>
			</rich:column>
			<rich:column sortable="true"  id="col_2"
				    
				    sortBy="#{cap.stateName}"
					filterBy="#{cap.stateName}" 
					
					filterEvent="onkeyup" width="170px"
					label="State Name">
				<f:facet name="header">
					<h:outputText value="State Name" id="state_name"/>
				</f:facet>
				<h:outputText value="#{cap.stateName}" id="cap_state"/>
			</rich:column>
			<rich:column sortable="true"  id="col_3"
					
					sortBy="#{cap.capitalName}"
					filterBy="#{cap.capitalName}"
					
					filterEvent="onkeyup" width="170px"
					label="State Capital">
				<f:facet name="header">
					<h:outputText value="State Capital" id="state_capital"/>
				</f:facet>
				<h:outputText value="#{cap.capitalName}" id="cap_name"/>
			</rich:column>
			<rich:column sortable="false" label="Time Zone" id="col_4">
				<f:facet name="header">
					<h:outputText value="Time Zone" id="time_zone"/>
				</f:facet>
				<h:outputText value="#{cap.timeZone}" id="cap_time_zone"/>
			</rich:column>
			<a4j:support reRender="selectiontable" id="extended_table_bean_take_selection"
					action="#{extendedTableBean.takeSelection}"
					event="onselectionchange" />
		</rich:extendedDataTable>
		<h:panelGroup layout="block" style="width:250px">
			<rich:panel>
				<f:facet name="header">
					<h:outputText value="Sort/Selection modes changing" />
				</f:facet>
				<h:panelGrid columns="2">
					<h:outputText value="Sort Mode:" />
					<h:selectOneMenu value="#{extendedTableBean.sortMode}">
						<f:selectItem itemLabel="Single" itemValue="single" />
						<f:selectItem itemLabel="Multi" itemValue="multi" />
						<a4j:support event="onchange" ajaxSingle="true" reRender="table" id="support_sort_onchange"/>
					</h:selectOneMenu>
					<h:outputText value="Selection Mode:" />
					<h:selectOneMenu value="#{extendedTableBean.selectionMode}">
						<a4j:support ajaxSingle="true" event="onchange" reRender="table" id="support_select_onchange"/>
						<f:selectItem itemLabel="Single" itemValue="single" />
						<f:selectItem itemLabel="Multi" itemValue="multi" />
						<f:selectItem itemLabel="None" itemValue="none" />
					</h:selectOneMenu>
				</h:panelGrid>
			</rich:panel>
			<rich:panel>
				<f:facet name="header">
					<h:outputText value="Currently selected rows:" />
				</f:facet>
				<rich:dataTable value="#{extendedTableBean.selectedCapitals}"
						var="sel" id="selectiontable">
					<rich:column>
						<h:graphicImage value="#{sel.stateFlag}" />
					</rich:column>
					<rich:column>
						<h:outputText value="#{sel.state}" />
					</rich:column>
					<rich:column>
						<h:outputText value="#{sel.name}" />
					</rich:column>
					<rich:column>
						<h:outputText value="#{sel.timeZone}" />
					</rich:column>
				</rich:dataTable>
			</rich:panel>
		</h:panelGroup>
	</h:panelGrid>
</h:form>
</f:view>
</body>
</html>