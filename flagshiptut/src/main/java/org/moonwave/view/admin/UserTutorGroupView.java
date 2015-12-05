package org.moonwave.view.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.moonwave.jpa.bo.RoleBO;
import org.moonwave.jpa.bo.TutorGroupBO;
import org.moonwave.jpa.bo.UserBO;
import org.moonwave.jpa.bo.UserRoleBO;
import org.moonwave.jpa.model.Role;
import org.moonwave.jpa.model.TutorGroup;
import org.moonwave.jpa.model.User;
import org.moonwave.jpa.model.UserRole;
import org.moonwave.view.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User TutorGroup View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class UserTutorGroupView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(UserTutorGroupView.class);

    private String selectedTutorGroupId;
    private List<TutorGroup> tutorGroups;

    private String selectedUserId;
    private List<User> users;
    private List<User> usersInTutorGroup;

    @PostConstruct
    public void init() {

        tutorGroups = new TutorGroupBO().getAllGroups();
        users = new UserBO().findAllUsers();
        // get a list of users for the first role
        if (!tutorGroups.isEmpty()) {
            selectedTutorGroupId = String.valueOf(tutorGroups.get(0).getId());
            getUsersByRoleId(selectedTutorGroupId);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public String getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(String selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public String getSelectedRoleId() {
        return selectedTutorGroupId;
    }

    public void setSelectedRoleId(String selectedRoleId) {
        this.selectedTutorGroupId = selectedRoleId;
    }

    public List<TutorGroup> getRoles() {
        return tutorGroups;
    }

    public List<User> getUsersInTutorGroup() {
        return usersInTutorGroup;
    }

    /**
     * Handle role change event
     * Search userrole table by using role
     *
     * @param event
     */
    public void changeRole(AjaxBehaviorEvent event) {
        getUsersByRoleId(selectedTutorGroupId);
    }

    /**
     * Handle removing user from a selected role
     * or
     * public String removeUserFromRole()
     * @return
     */
    public void removeUserFromTutorGroup() {
        try {
//            UserRole userRole = new UserRoleBO().findByRoleUser(Short.parseShort(selectedTutorGroupId), Integer.parseInt(selectedUserId));
//            if (userRole != null) {
//                super.getBasebo().remove(userRole);
//            }

            getUsersByRoleId(selectedTutorGroupId);
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
//            UserRole userRole = new UserRoleBO().findByRoleUser(Short.parseShort(selectedTutorGroupId), Integer.parseInt(selectedUserId));
//            if (userRole != null) {
//                super.info("User is already in selected role");
//                return null;
//            }
//
//            userRole = new UserRole();
//            userRole.setUserId(Integer.parseInt(selectedUserId));
//            userRole.setRoleId(Short.parseShort(selectedTutorGroupId));
//            userRole.setCreateTime(super.getSqlTimestamp());
//            super.getBasebo().persist(userRole);

            getUsersByRoleId(selectedTutorGroupId);
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
        	TutorGroup role = new TutorGroupBO().findById(Short.parseShort(selectedRoleId));
            usersInTutorGroup = role.getUsers();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}