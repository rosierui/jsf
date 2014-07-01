<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>	

<!-- 
http://docs.jboss.org/richfaces/latest_3_3_X/en/tlddoc/rich/column.html
 -->

	<script type="text/javascript">
		function setCaretToEnd (e) {
			var control = $((e.target ? e.target : e.srcElement).id);
  			if (control.createTextRange) {
    			var range = control.createTextRange();
    			range.collapse(false);
    			range.select();
  			}
  			else if (control.setSelectionRange) {
    			control.focus();
    			var length = control.value.length;
    			control.setSelectionRange(length, length);
  			}
  			control.selectionStart = control.selectionEnd = control.value.length;
		} 

	</script>
</head>
<body>
<f:view>
	<h:form>
		<rich:dataTable value="#{capitalsBean.capitals}" var="cap" id="table" rows="20">
			<f:facet name="header">
				<rich:columnGroup>
					<rich:column colspan="2">
						<h:outputText value="Filtering Example" />
					</rich:column>
					<rich:column breakBefore="true">
						<h:outputText value="State Name" />
					</rich:column>
					<rich:column>
						<h:outputText value="State Time Zone" />
					</rich:column>
				</rich:columnGroup>
			</f:facet>
			<rich:column filterMethod="#{filteringBean.filterStates}">
				<f:facet name="header">
					<h:inputText value="#{filteringBean.filterValue}" id="input">
						<a4j:support event="onkeyup" reRender="table , ds2"
							ignoreDupResponses="true" requestDelay="700"
							oncomplete="setCaretToEnd(event);"
							/>
					</h:inputText>
				</f:facet>
				<h:outputText value="#{cap.state}" />
			</rich:column>
			<rich:column
				filterExpression="#{fn:containsIgnoreCase(cap.timeZone, filteringBean.filterZone)}">
				<f:facet name="header">
					<h:selectOneMenu value="#{filteringBean.filterZone}">
						<f:selectItems value="#{filteringBean.filterZones}" />
						<a4j:support event="onchange" reRender="table, ds2" />
					</h:selectOneMenu>
				</f:facet>
				<h:outputText value="#{cap.timeZone}" />
			</rich:column>
			<f:facet name="footer">
				<rich:datascroller id="ds2" renderIfSinglePage="false"></rich:datascroller>
			</f:facet>
		</rich:dataTable>		
	</h:form>
</body>	
</f:view>
</html>
