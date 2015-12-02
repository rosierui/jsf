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
package org.moonwave.view.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.moonwave.domain.Car;
import org.moonwave.jpa.bo.UserBO;
import org.moonwave.jpa.model.User;
import org.moonwave.service.CarService;
import org.moonwave.view.BaseView;
/**
 * checkboxView
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class UserroleView extends BaseView {

    private static final long serialVersionUID = 1L;

    private String[] selectedConsoles;
    private String selectedCity;
    private List<String> roles;

    private List<Car> cars;
    private List<User> users;
    private User current;
    private String role;

    @ManagedProperty("#{carService}")
    private CarService service;
 
    public List<Car> getCars() {
        return cars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @PostConstruct
    public void init() {
        roles = new ArrayList<String>();
        roles.add("San Francisco");
        roles.add("London");
        roles.add("Paris");
        roles.add("Istanbul");
        roles.add("Berlin");
        roles.add("Barcelona");
        roles.add("Rome");
        roles.add("Sao Paulo");
        roles.add("Amsterdam");

        cars = service.createCars(10);

        Map<String, String> rqm = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        StringBuilder sb = new StringBuilder();
        sb.append("&projectId=").append(rqm.get("projectId"));

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("setup") != null
                && FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("setup").equals("true")) {
            boolean volumesSetup = true;
        }
        // get a list of users
        users = new UserBO().getAllUsers();
    }

    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }

    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public List<String> getCities() {
        return roles;
    }

    public void save() {
        FacesMessage msg;
//        if(city != null && country != null)
            msg = new FacesMessage("Selected", "save() called");
//        else
//            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
        // save data to database
        StringBuffer sb = new StringBuffer();
//        for (String item : selectedCity) {
//            sb.append(item).append(", ");
//        }
        String temp = sb.toString();
        msg = new FacesMessage("Selected", temp);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String save2() {
        FacesMessage msg;
//        if(city != null && country != null)
            msg = new FacesMessage("Selected", "save() called");
//        else
//            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
        // save data to database
        StringBuffer sb = new StringBuffer();
//        for (String item : selectedCity) {
//            sb.append(item).append(", ");
//        }
        String temp = sb.toString();
        msg = new FacesMessage("Selected", temp);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
}
