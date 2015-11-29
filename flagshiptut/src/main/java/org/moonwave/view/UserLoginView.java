package org.moonwave.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.moonwave.jpa.model.User;
import org.moonwave.util.SHAUtil;

@ManagedBean
public class UserLoginView extends BaseView {

    private String username;
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isUserLogggedIn() {
        Object ret = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("isUserLoggedIn");
        Object user = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        return (ret != null) ? true : false; 
    }

    public String login() {
        EntityManager em = super.getBasebo().getEntityManager();
        Query query = em.createQuery("Select u from User u where u.loginId = ?1 and u.password = ?2", User.class);
        query.setParameter(1, username);
        query.setParameter(2, SHAUtil.encryptPassword(password));
        List<User> list = query.getResultList();
        User user;
        if (list.isEmpty()) {
            super.error("Invalid credentials");
            return "";
        }
        // login successful
        user = list.get(0);
        User loggedInUser = new User();
        loggedInUser.setFirstName(user.getFirstName());
        loggedInUser.setLastName(user.getLastName());
        loggedInUser.setLoginId(user.getLoginId());
        loggedInUser.setTimezone(user.getTimezone());
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setPhone(user.getPhone());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isUserLoggedIn", Boolean.TRUE);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", loggedInUser);
        return "/admin/newuser?faces-redirect=true";
    }
}