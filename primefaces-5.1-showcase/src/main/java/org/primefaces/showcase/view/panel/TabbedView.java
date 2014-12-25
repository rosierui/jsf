/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.panel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.tabview.Tab;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.showcase.domain.Car;

import javax.faces.component.html.HtmlSelectOneMenu;

@ManagedBean
public class TabbedView {
    
    private List<Car> cars;

    private TabView tabView;
    
    private String allocationCycle;
    
    private HtmlSelectOneMenu ctrlCycle;
    
    @PostConstruct
    public void init() {
        cars = new ArrayList<Car>();
        cars.add(new Car("Y25YIH5", "Fiat", 2014, "Black", 10000, true));
        cars.add(new Car("JHF261G", "BMW", 2013, "Blue", 50000, true));
        cars.add(new Car("HSFY23H", "Ford", 2012, "Black", 35000, false));
        cars.add(new Car("GMDK353", "Volvo", 2014, "White", 40000, true));
        cars.add(new Car("345GKM5", "Jaguar", 2011, "Gray", 48000, false));
    }
    
    public List<Car> getCars() {
        return cars;
    }
    
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", event.getTab().getId() + " - " + event.getTab().getTitle() + ", index: " + this.getActiveTabIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
        
    public void onTabClose(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public TabView getTabView() {
        return tabView;
    }

    public void setTabView(TabView tabView) {
        this.tabView = tabView;
    }

    public int getActiveTabIndex() {
        int activeTabIndex = tabView.getActiveIndex();
        return activeTabIndex;
    }

    /**
     * 0-indexed
     *
     * @param tabIdx
     */
    public void setActiveTab(int tabIdx) {
        tabView.setActiveIndex(tabIdx);
    }

    public void nextTab() {
        int count1 = tabView.getChildCount(); 
        int index  = tabView.getIndex(); 

        int activeTabIdx = tabView.getActiveIndex();
        List<UIComponent> children = tabView.getChildren();
        ((Tab) (children.get(activeTabIdx + 1))).setDisabled(false); // active the next one
        
        if (activeTabIdx + 1 == 1) { // the 2nd tab
            this.ctrlCycle.setRequired(true);
        }
        // get the total tab counts
        int count = children.size();
        if (activeTabIdx < count) {
            tabView.setActiveIndex(activeTabIdx + 1);
        }
    }

    public void previousTab() {
        int count1 = tabView.getChildCount(); 
        int index  = tabView.getIndex();

        int activeTabIdx = tabView.getActiveIndex();
        if (activeTabIdx > 0) {
        	tabView.setActiveIndex(activeTabIdx - 1);
        }
    }

    /*
     * <p:tabView dynamic="true" cache="true" effect="fold">
     * cache="true": just call once when tab is activated
     * cache="true": call every time when tab is activated
     */
    public String getTab1Text() {
        return "The story begins as Don Vito Corleone, the head of a New York Mafia family, oversees his daughter's wedding. " + 
        "His beloved son Michael has just come home from the war, but does not intend to become part of his father's business. "  +
        "Through Michael's life the nature of the family business becomes clear. The business of the family is just like the head" +
        " of the family, kind and benevolent to those who give respect, " + 
        "but given to ruthless violence whenever anything stands against the good of the family.";
    }
    public String getTab2Text() {
        return "Francis Ford Coppola's legendary continuation and sequel to his landmark 1972 film, The_Godfather, parallels the young Vito Corleone's rise with his son Michael's spiritual fall, deepening The_Godfather's depiction of the dark side of the American dream." + 
        "In the early 1900s, the child Vito flees his Sicilian village for America after the local Mafia kills his family. Vito struggles to make a living, legally or illegally, for his wife and growing brood in Little Italy, " +
        "killing the local Black Hand Fanucci after he demands his customary cut of the tyro's business. With Fanucci gone, Vito's communal stature grows.";
    }
    public String getTab3Text() {
        return "After a break of more than 15 years, director Francis Ford Coppola and writer Mario Puzo returned to the well for this third and final story of the fictional Corleone crime family. " +
        "Two decades have passed, and crime kingpin Michael Corleone, now divorced from his wife Kay has nearly succeeded in keeping his promise that his family would one day be completely legitimate.";
    }
    public String getTab4Text() {
        return "This is Tab 4";
    }
    public String getTab5Text() {
        return "This is Tab 5";
    }

    public HtmlSelectOneMenu getCtrlCycle() {
		return ctrlCycle;
	}

	public void setCtrlCycle(HtmlSelectOneMenu ctrlCycle) {
		this.ctrlCycle = ctrlCycle;
	}

	public String getAllocationCycle() {
		return allocationCycle;
	}

	public void setAllocationCycle(String allocationCycle) {
		this.allocationCycle = allocationCycle;
	}

	public List<SelectItem> getAllocationCycles() {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        selectItems.add(new SelectItem("01", "Cycle - 1"));
        selectItems.add(new SelectItem("02", "Cycle - 2"));
        selectItems.add(new SelectItem("03", "Cycle - 3"));
        selectItems.add(new SelectItem("04", "Cycle - 4"));
        selectItems.add(new SelectItem("05", "Cycle - 5"));
        return selectItems;
    }
}
