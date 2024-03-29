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
.top {
	vertical-align: top;
}

.equals {
	width: 33%;
}
</style>
</head>
<body>
<f:view>
	<h:panelGrid columns="2" columnClasses="top">
		<h:form>
			<rich:dataTable value="#{dataTableScrollerBean.allCars}"
				var="category" rows="20" id="table" reRender="ds2"
				sortPriority="#{sortingBean.prioritList}">
				<rich:column id="make" sortBy="#{category.make}"
					sortOrder="#{sortingBean.makeDirection}" selfSorted="false">
					<f:facet name="header">
						<h:outputText styleClass="headerText" value="Make" />
					</f:facet>
					<h:outputText value="#{category.make}" />
				</rich:column>
				<rich:column id="model" sortBy="#{category.model}"
					sortOrder="#{sortingBean.modelDirection}" selfSorted="false">
					<f:facet name="header">
						<h:outputText styleClass="headerText" value="Model" />
					</f:facet>
					<h:outputText value="#{category.model}" />
				</rich:column>
				<rich:column id="price" sortBy="#{category.price}"
					sortOrder="#{sortingBean.priceDirection}" selfSorted="false">
					<f:facet name="header">
						<h:outputText styleClass="headerText" value="Price" />
					</f:facet>
					<h:outputText value="#{category.price}" />
				</rich:column>
				<rich:column id="mileage" sortBy="#{category.mileage}"
					sortOrder="#{sortingBean.mileageDirection}" selfSorted="false">
					<f:facet name="header">
						<h:outputText styleClass="headerText" value="Mileage" />
					</f:facet>
					<h:outputText value="#{category.mileage}" />
				</rich:column>
				<rich:column width="200px" id="vin">
					<f:facet name="header">
						<h:outputText styleClass="headerText" value="VIN" />
					</f:facet>
					<h:outputText value="#{category.vin}" />
				</rich:column>
				<rich:column id="stock">
					<f:facet name="header">
						<h:outputText styleClass="headerText" value="Stock" />
					</f:facet>
					<h:outputText value="#{category.stock}" />
				</rich:column>
				<f:facet name="footer">
					<rich:datascroller id="ds2"></rich:datascroller>
				</f:facet>
			</rich:dataTable>
		</h:form>
		<h:form>
			<rich:panel id="sortPanel">
				<f:facet name="header">
					<h:outputText value="Specify Sorting Conditions:" />
				</f:facet>
				<h:panelGrid columns="1">
					<a4j:outputPanel>
						<h:panelGrid columns="3" width="300px"
							columnClasses="equals,equals,equals">
							<h:outputText value="First sort by:" />
							<h:selectOneMenu value="#{sortingBean.firstSortOption.item}"
								valueChangeListener="#{sortingBean.firstSortOptionValueChanged}">
								<a4j:support event="onchange" reRender="sortPanel"
									ajaxSingle="true" />
								<f:selectItems value="#{sortingBean.firstSortItems}" />
							</h:selectOneMenu>
							<h:selectOneMenu value="#{sortingBean.firstSortOption.direction}">
								<a4j:support event="onchange" reRender="sortPanel" />
								<f:selectItems value="#{sortingBean.sortDirectionItems}" />
							</h:selectOneMenu>
						</h:panelGrid>
					</a4j:outputPanel>

					<a4j:outputPanel
						rendered="#{(!(sortingBean.firstSortOption.item == 0))and(!(sortingBean.firstSortOption.direction == 'UNSORTED'))}">
						<h:panelGrid columns="3" width="300px"
							columnClasses="equals,equals,equals">
							<h:outputText value="Second sort by:" />
							<h:selectOneMenu value="#{sortingBean.secondSortOption.item}"
								valueChangeListener="#{sortingBean.secondSortOptionValueChanged}">
								<a4j:support event="onchange" reRender="sortPanel"
									ajaxSingle="true" />
								<f:selectItems value="#{sortingBean.secondSortItems}" />
							</h:selectOneMenu>
							<h:selectOneMenu
								value="#{sortingBean.secondSortOption.direction}">
								<a4j:support event="onchange" reRender="sortPanel" />
								<f:selectItems value="#{sortingBean.sortDirectionItems}" />
							</h:selectOneMenu>
						</h:panelGrid>
					</a4j:outputPanel>

					<a4j:outputPanel
						rendered="#{(!(sortingBean.secondSortOption.item == 0))and(!(sortingBean.firstSortOption.item == 0))and(!(sortingBean.secondSortOption.direction == 'UNSORTED'))}">
						<h:panelGrid columns="3" width="300px"
							columnClasses="equals,equals,equals">
							<h:outputText value="Third sort by:" />
							<h:selectOneMenu value="#{sortingBean.thirdSortOption.item}"
								valueChangeListener="#{sortingBean.thirdSortOptionValueChanged}">
								<a4j:support event="onchange" reRender="sortPanel"
									ajaxSingle="true" />
								<f:selectItems value="#{sortingBean.thirdSortItems}" />
							</h:selectOneMenu>
							<h:selectOneMenu value="#{sortingBean.thirdSortOption.direction}">
								<a4j:support event="onchange" reRender="sortPanel" />
								<f:selectItems value="#{sortingBean.sortDirectionItems}" />
							</h:selectOneMenu>
						</h:panelGrid>
					</a4j:outputPanel>
				</h:panelGrid>
				<a4j:commandButton value="Sort Table"
					action="#{sortingBean.sortTable}" reRender="table"
					disabled="#{sortingBean.firstSortOption.direction=='UNSORTED' || sortingBean.firstSortOption.item == 0}" />
			</rich:panel>
		</h:form>
	</h:panelGrid>
</f:view>
</body>
</html>