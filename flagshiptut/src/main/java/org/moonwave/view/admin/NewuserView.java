package org.moonwave.view.admin;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    private List<String> timezones;

    User user;

    @PostConstruct
    public void init() {
        timezones = new ArrayList<String>();

        String[] availableTZs = TimeZone.getAvailableIDs();
        List<String> tzs = Arrays.asList(availableTZs);
        Collections.sort(tzs);
        timezones.add("");
        timezones.addAll(tzs);
        if (user == null) {
            user = new User();
        }
        user.setTimezone("America/Phoenix"); // get from properties
    }

    public List<String> getCities() {
        return timezones;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public String save() {
        FacesMessage msg;
//        if(city != null && country != null)
            msg = new FacesMessage("Selected", "save() called");
//        else
//            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
        // save data to database
        StringBuffer sb = new StringBuffer();
        msg = new FacesMessage("Selected", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
}
