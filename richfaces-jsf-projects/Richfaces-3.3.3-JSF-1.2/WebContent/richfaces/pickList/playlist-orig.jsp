<!doctype html public "-//w3c//dtd html 4.0 transitional//en">


<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

	<!--  copied from Richfaces-demo-3.3.3.Final/richfaces/orderingList/example/playlist.xhtml -->
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
		<h:panelGrid columns="2" columnClasses="top 70per, top 30per" width="100%">
		<rich:orderingList value="#{library.songsList}" var="lib" listHeight="300" listWidth="350" 
						   converter="orderingListConverter" selection="#{library.selectedSongsSet}">
			<rich:column  width="180">
			<f:facet name="header">
				<h:outputText value="Song Name" />
			</f:facet> 
				<h:outputText value="#{lib.title}"></h:outputText>
			</rich:column>
			<rich:column> 
				<f:facet name="header">
					<h:outputText value="Artist Name" />
				</f:facet>
				<h:outputText value="#{lib.album.artist.name}"></h:outputText><br/>
			</rich:column>
			<a4j:support event="onclick" ignoreDupResponses="true" requestDelay="500" action="#{library.takeSelection}" reRender="output"/>
			<a4j:support event="onkeyup" ignoreDupResponses="true" requestDelay="500" action="#{library.takeSelection}" reRender="output"/>
		</rich:orderingList>
		<rich:panel id="output" header="Current Selection" style="width:200px">
			<rich:dataList value="#{library.selectedSongsList}" var="song" rendered="#{not empty library.selectedSongsList}">
				<h:outputText value="#{song.title}"></h:outputText>
			</rich:dataList>
			<h:outputText value="No Songs Selected" rendered="#{empty library.selectedSongsList}"/>
		</rich:panel>
		</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>