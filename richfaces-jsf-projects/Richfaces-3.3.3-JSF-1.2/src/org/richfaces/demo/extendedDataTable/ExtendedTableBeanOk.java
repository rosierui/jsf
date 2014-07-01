/**
 * 
 */
package org.richfaces.demo.extendedDataTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.demo.capitals.Capital;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

/**
 * @author Ilya Shaikovsky
 * 
	<h:panelGrid columns="2" columnClasses="top , top">
		<rich:extendedDataTable
				value="#{extendedTableBean.capitalsDataModel}" var="cap" id="table"
				width="580px" height="400px"
				sortMode="#{extendedTableBean.sortMode}"
				selectionMode="#{extendedTableBean.selectionMode}"
				tableState="#{extendedTableBean.tableState}"
				selection="#{extendedTableBean.selection}">
 * 
 *
 */
public class ExtendedTableBeanOk {

	private String sortMode="single"; // single / multiple

	private String selectionMode="multi"; // // single / multiple / none

	private Object tableState;

	private List<Capital> capitals = new ArrayList<Capital>(); // primary data store, pass into dataModel
	private ExtendedTableDataModel<Capital> dataModel;

	private Selection selection = new SimpleSelection();
	private List<Capital> selectedCapitals = new ArrayList<Capital>();
	
	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public ExtendedTableBeanOk() {
		dataModel = new ExtendedTableDataModel<Capital>(new DataProvider<Capital>(){
			private static final long serialVersionUID = 5054087821033164847L;

			public Capital getItemByKey(Object key) {
				for(Capital c : capitals){ // data here held in a list, can be held in a map 
					if (key.equals(getKey(c))){
						return c;
					}
				}
				return null;
			}

			public List<Capital> getItemsByRange(int firstRow, int endRow) {
				return capitals.subList(firstRow, endRow); // retrieved in a preloaded list or retrieve dynamically from database 
			}

			public Object getKey(Capital item) {
				return item.getName();
			}

			public int getRowCount() {
				return capitals.size();
			}
			// methods copied from ExtendedTableDataModel<T>
			
			/**
			 * This method never called from framework.
			 * (non-Javadoc)
			 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
			 */
			public Object getRowKey() {
				return getRowKey();
			}

			/**
			 * This method normally called by Visitor before request Data Row.
			 * (non-Javadoc)
			 * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(java.lang.Object)
			 */
			public void setRowKey(Object key) {
				setRowKey(key);
			}

			/** 
			 * This is main part of Visitor pattern. Method called by framework many times during request processing. 
			 * (non-Javadoc)
			 * @see org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext, org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range, java.lang.Object)
			 */
			public void walk(FacesContext context, DataVisitor visitor, Range range,
					Object argument) throws IOException {
				walk(context, visitor, range, argument);
			}//walk
			
			public Capital getObjectByKey(Object key) {
				return getObjectByKey(key);
			}
			public int getRowIndex() {
				//throw new UnsupportedOperationException();
				return getRowIndex();
			}

			/**
			 * Unused rudiment from old JSF staff.
			 * (non-Javadoc)
			 * @see javax.faces.model.DataModel#setRowIndex(int)
			 */
			public void setRowIndex(int rowIndex) {
				//throw new UnsupportedOperationException();
				setRowIndex(rowIndex);
			}
			
		});		
	}

	public void takeSelection() {
		selectedCapitals.clear();
		Iterator<Object> iterator = getSelection().getKeys();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			selectedCapitals.add(dataModel.getObjectByKey(key));
		}
	}

	public ExtendedTableDataModel<Capital> getCapitalsDataModel() {
		return dataModel;
	}

	/*
	<managed-bean>
	<managed-bean-name>extendedTableBean</managed-bean-name>
	<managed-bean-class>org.richfaces.demo.extendedDataTable.ExtendedTableBean</managed-bean-class>
	<managed-bean-scope>session</managed-bean-scope>
	<managed-property>
		<property-name>capitals</property-name>
		<value>#{capitalsBean.capitals}</value>
	</managed-property>
	</managed-bean>
	*/
	public void setCapitals(List<Capital> capitals) {
		this.capitals = capitals;
	}

	public Object getTableState() {
		return tableState;
	}

	public void setTableState(Object tableState) {
		this.tableState = tableState;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public List<Capital> getSelectedCapitals() {
		return selectedCapitals;
	}

	public void setSelectedCapitals(List<Capital> selectedCapitals) {
		this.selectedCapitals = selectedCapitals;
	}

}
