package org.richfaces.demo.filteringFeature;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import org.richfaces.demo.capitals.Capital;

/**
 * @author Ilya Shaikovsky
 * 
 */
public class FilteringBean {

	private String filterZone = "5";
	private String filterValue="";
	private ArrayList<SelectItem> filterZones = new ArrayList<SelectItem>();

	public boolean filterStates(Object current) { // called by framework
		Capital currentCapital = (Capital)current;
		if (filterValue.length()==0) {
			return true;
		}
		if (currentCapital.getState().toLowerCase().startsWith(filterValue.toLowerCase())) {
			return true;
		}else {
			return false; 
		}
	}

	public FilteringBean() {
		for (int i = 5; i < 11; i++) {
			SelectItem select = new SelectItem();
			select.setLabel("-" + i);
			select.setValue(i);
			filterZones.add(select); // create -5 to -10 time zone pick list
		}
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue; // user entered state name filter value
	}

	public String getFilterZone() {
		return filterZone;
	}

	public void setFilterZone(String filterZone) {
		this.filterZone = filterZone; // user picked time zone value
	}

	public ArrayList<SelectItem> getFilterZones() {
		return filterZones;
	}
}
