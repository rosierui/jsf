package org.moonwave.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.moonwave.jpa.model.Role;
import org.moonwave.jpa.model.User;

@ManagedBean
@SessionScoped
public class AccessController implements Serializable {

    private static final long serialVersionUID = 1L;

    static final String student    = "student";
    static final String tutor      = "tutor";
    static final String teacher    = "teacher";
    static final String supervisor = "supervisor";
    static final String admin      = "admin";

    @ManagedProperty("#{loginController}")
    private LoginController loginController;

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public boolean isStudent() {
        return isInRole(student);
    }
 
    public boolean isTutor() {
        return isInRole(tutor);
    }
    public boolean isTeacher() {
        return isInRole(teacher);
    }

    public boolean isSupervisor() {
        return isInRole(supervisor);
    }

    public boolean isAdmin() {
        return isInRole(admin);
    }

    private boolean isInRole(String roleAlias) {
        boolean ret = false;
        User user = loginController.getLoggedInUser();
        if ((user != null) && (user.getRoles() != null)) {
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (role.getAlias().equals(roleAlias)) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }
}