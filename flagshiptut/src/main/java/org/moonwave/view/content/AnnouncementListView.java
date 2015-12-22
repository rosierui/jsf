package org.moonwave.view.content;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.bo.RoleBO;
import org.moonwave.jpa.bo.UserBO;
import org.moonwave.jpa.model.Announcement;
import org.moonwave.jpa.model.Role;
import org.moonwave.jpa.model.User;
import org.moonwave.view.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Announcement List View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class AnnouncementListView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(AnnouncementListView.class);

    private String selectedRoleId;
    private List<Role> roles;

    private String selectedId;
    private List<User> users;
    private List<User> usersInRole;

    private List<Announcement> data;
    private Announcement current;

    @PostConstruct
    public void init() {

        // get a list of group posts
        GenericBO<Announcement> bo = new GenericBO<>(Announcement.class);
        data = bo.findAll();

        // get a list of roles
        roles = new RoleBO().findAllRoles();
        // get a list of all users
        users = new UserBO().findActiveUsers();
        // get a list of users for the first role
        if (!roles.isEmpty()) {
            selectedRoleId = String.valueOf(roles.get(0).getId());
            getUsersByRoleId(selectedRoleId);
        }
    }

    public Announcement getCurrent() {
        return current;
    }

    public void setCurrent(Announcement current) {
        this.current = current;
    }

    public List<Announcement> getData() {
        return data;
    }

    public void setData(List<Announcement> data) {
        this.data = data;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
        if ((this.selectedId != null) && !this.selectedId.isEmpty()) {
            GenericBO<Announcement> bo = new GenericBO<>(Announcement.class);
            this.current = bo.findById(Integer.parseInt(selectedId));
        }
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

    public List<User> getUsersInRole() {
        return usersInRole;
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
            Role role = new RoleBO().findById(Short.parseShort(selectedRoleId));
            User user = new UserBO().findById(Integer.parseInt(selectedId));
            List<User> users = (role.getUsers() != null) ? role.getUsers() : new ArrayList<User>();
            for (User u : users) {
                if (u.getId() == user.getId()) {
                    // work on master object in a many-to-many relationship
                    List<Role> roles = (user.getRoles() != null) ? user.getRoles() : new ArrayList<Role>();
                    roles.remove(role);
                    user.setRoles(roles);
                    super.getBasebo().update(user);
                    break;
                }
            }

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
            Role role = new RoleBO().findById(Short.parseShort(selectedRoleId));
            List<User> users = (role.getUsers() != null) ? role.getUsers() : new ArrayList<User>();
            for (User user : users) {
                if (user.getId() == Integer.parseInt(selectedId)) {
                  super.info("User is already in selected role");
                  return null;
                }
            }
            // work on master object in a many-to-many relationship
            User user = new UserBO().findById(Integer.parseInt(selectedId));
            List<Role> roles = (user.getRoles() != null) ? user.getRoles() : new ArrayList<Role>();  
            roles.add(role);
            user.setRoles(roles);
            super.getBasebo().update(user);

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
            Role role = new RoleBO().findById(Short.parseShort(selectedRoleId));
            usersInRole = role.getUsers();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}