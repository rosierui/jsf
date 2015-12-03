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
import javax.faces.event.AjaxBehaviorEvent;

import org.moonwave.domain.Car;
import org.moonwave.jpa.bo.RoleBO;
import org.moonwave.jpa.bo.UserBO;
import org.moonwave.jpa.bo.UserRoleBO;
import org.moonwave.jpa.model.Role;
import org.moonwave.jpa.model.User;
import org.moonwave.jpa.model.UserRole;
import org.moonwave.service.CarService;
import org.moonwave.view.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * UserroleView
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class UserroleView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(NewuserView.class);

    private String selectedRoleId;
    private List<Role> roles;

    private List<Car> cars;
    private List<User> users;
    private String current; // current user id
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

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
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
        cars = service.createCars(10);

        Map<String, String> rqm = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        StringBuilder sb = new StringBuilder();
        sb.append("&projectId=").append(rqm.get("projectId"));

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("setup") != null
                && FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("setup").equals("true")) {
            boolean volumesSetup = true;
        }
        // get a list of roles
        roles = new RoleBO().getAllRoles();
        // get a list of users
        users = new UserBO().getAllUsers();
    }

    public String getSelectedRoleId() {
        return selectedRoleId;
    }

    public void setSelectedRoleId(String selectedRoleId) {
        this.selectedRoleId = selectedRoleId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Handle role change event
     * Search userrole table by using role
     *
     * @param event
     */
    public void changeRole(AjaxBehaviorEvent event) {
        getUsersByRoleId(selectedRoleId);
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

    /**
     * Handle adding user to selected role
     * @return
     */
    public String addUserToRole() {
    	UserRole userRole = new UserRole();
    	userRole.setUserId(Integer.parseInt(current));
    	userRole.setRoleId(Short.parseShort(selectedRoleId));
    	userRole.setCreateTime(super.getSqlTimestamp());
    	super.getBasebo().persist(userRole);
    	
    	getUsersByRoleId(selectedRoleId);
    	
    	FacesMessage msg;
        msg = new FacesMessage("Selected", "save() called");
        StringBuffer sb = new StringBuffer();
        String temp = sb.toString();
        msg = new FacesMessage("Selected", temp);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }

    private void getUsersByRoleId(String selectedRoleId) {
        try {
            List<UserRole> useroles = new UserRoleBO().findByRole(Integer.parseInt(selectedRoleId));
            List<Integer> userIds = new ArrayList<>();
            for (UserRole ur : useroles) {
                userIds.add(ur.getUserId());
            }
            if (!userIds.isEmpty()) {
                users = new UserBO().findInIds(userIds);
            } else {
                users = new ArrayList<>();
            }
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
        }
    	
    }
}