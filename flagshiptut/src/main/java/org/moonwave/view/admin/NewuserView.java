package org.moonwave.view.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.moonwave.jpa.model.User;

/**
 * NewuserView
 * @author moonwave
 *
 */
@ManagedBean
public class NewuserView {
    
    private String[] selectedConsoles;  
    private String[] selectedCities; 
    private List<String> cities;

    User user;

    @PostConstruct
    public void init() {
        cities = new ArrayList<String>();
		TimeZone timeZone = Calendar.getInstance().getTimeZone();

		String[] availableTZs = timeZone.getAvailableIDs();
		List<String> tzs = Arrays.asList(availableTZs);
		Collections.sort(tzs);
		cities.add("");
		cities.addAll(tzs);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
