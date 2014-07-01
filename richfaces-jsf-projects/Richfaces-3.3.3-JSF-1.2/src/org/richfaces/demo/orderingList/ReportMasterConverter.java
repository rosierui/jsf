package org.richfaces.demo.orderingList;


import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.richfaces.demo.ajaxlist.AvMasterMaintenanceHandler;
import org.richfaces.demo.ajaxlist.AvReportMaster;


public class ReportMasterConverter implements Converter{

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		long id = Long.parseLong(value); 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = facesContext.getApplication()
		.getExpressionFactory();

		ValueExpression beanExpression = expressionFactory
		.createValueExpression(facesContext.getELContext(),
				"#{avMasterMaintenanceHandler}", AvMasterMaintenanceHandler.class);		
		
//		ValueExpression beanExpression = expressionFactory
//		.createValueExpression(facesContext.getELContext(),
//				"#{library}", Library.class);		

		AvMasterMaintenanceHandler bkbean =  (AvMasterMaintenanceHandler)beanExpression.getValue(facesContext.getELContext());
		for (AvReportMaster song : bkbean.getSameClpnameList()) {
			if (song.getId() == id){
				return song;				
			}
			
		}
		
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		AvReportMaster song = (AvReportMaster)value;
		return new Long(song.getId()).toString();
	}

}
