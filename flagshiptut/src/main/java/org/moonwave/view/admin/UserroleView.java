package org.moonwave.view.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.moonwave.jpa.bo.RoleBO;
import org.moonwave.jpa.bo.UserBO;
import org.moonwave.jpa.bo.UserRoleBO;
import org.moonwave.jpa.model.Role;
import org.moonwave.jpa.model.User;
import org.moonwave.jpa.model.UserRole;
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
    static final Logger LOG = LoggerFactory.getLogger(UserroleView.class);

    private String selectedRoleId;
    private List<Role> roles;

    private String selectedUserId;
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public String getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(String selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    @PostConstruct
    public void init() {

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

    /**
     * Handle removing user from a selected role
     * or
     * public String removeUserFromRole()
     * @return
     */
    public void removeUserFromRole() {
        try {
            // TODO - check duplicates in the same role
        	selectedUserId = null;
            UserRole userRole = new UserRole();
            userRole.setUserId(Integer.parseInt(selectedUserId));
            userRole.setRoleId(Short.parseShort(selectedRoleId));
            userRole.setCreateTime(super.getSqlTimestamp());
            super.getBasebo().persist(userRole);

            getUsersByRoleId(selectedRoleId);
        } catch (Exception e) {
            super.error("Sorry, an error occurred, please contact your administrator");
            LOG.error("", e);
        }
    }

    /**
     * Handle adding user to a selected role
     * or
     * public void addUserToRole()
     * @return
     */
    public String addUserToRole() {
        try {
            // TODO - check duplicates in the same role
        	selectedUserId = null;
            UserRole userRole = new UserRole();
            userRole.setUserId(Integer.parseInt(selectedUserId));
            userRole.setRoleId(Short.parseShort(selectedRoleId));
            userRole.setCreateTime(super.getSqlTimestamp());
            super.getBasebo().persist(userRole);

            getUsersByRoleId(selectedRoleId);
        } catch (Exception e) {
            super.error("Sorry, an error occurred, please contact your administrator");
            LOG.error("", e);
        }
        return "";
    }

    // --------------------------------------------------------- Private Methods

    /**
     * Find a list of users by selected role id
     *
     * @param selectedRoleId
     */
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
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}