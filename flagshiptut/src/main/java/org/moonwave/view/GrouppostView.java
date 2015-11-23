package org.moonwave.view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * checkboxView
 * @author moonwave
 *
 */
@ManagedBean
public class GrouppostView {
    
    private String[] selectedConsoles;  
    private String[] selectedCities; 
    private List<String> cities;

    private String body;

    @PostConstruct
    public void init() {
        cities = new ArrayList<String>();
        cities.add("San Francisco");
        cities.add("London");
        cities.add("Paris");
        cities.add("Istanbul");
        cities.add("Berlin");
        cities.add("Barcelona");
        cities.add("Rome");
        cities.add("Sao Paulo");
        cities.add("Amsterdam");
    }

    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }

    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }

    public String[] getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(String[] selectedCities) {
        this.selectedCities = selectedCities;
    }

    public List<String> getCities() {
        return cities;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void save() {
        FacesMessage msg;
//        if(city != null && country != null)
            msg = new FacesMessage("Selected", "save() called");
//        else
//            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
        // save data to database
        StringBuffer sb = new StringBuffer();
        for (String item : selectedCities) {
            sb.append(item).append(", ");
        }
        String temp = sb.toString();
        msg = new FacesMessage("Selected", temp);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
