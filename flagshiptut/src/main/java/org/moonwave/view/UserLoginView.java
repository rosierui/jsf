package org.moonwave.view;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.moonwave.jpa.model.User;
import org.moonwave.util.SHAUtil;

@ManagedBean
@SessionScoped
public class UserLoginView extends BaseView {

    private static final long serialVersionUID = 1L;

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
        Object user = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        return (user != null) ? true : false; 
    }

    @SuppressWarnings("unchecked")
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
        loggedInUser.setId(user.getId());
        loggedInUser.setFirstName(user.getFirstName());
        loggedInUser.setLastName(user.getLastName());
        loggedInUser.setLoginId(user.getLoginId());
        loggedInUser.setTimezone(user.getTimezone());
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setPhone(user.getPhone());
        loggedInUser.setTag(user.getTag());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", loggedInUser);
        return "/dashboard?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", null);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/login?faces-redirect=true";
    }

    /**
     * http://stackoverflow.com/questions/4032825/how-to-make-a-redirection-in-jsf
     * http://stackoverflow.com/questions/12815529/jsf-index-xhtml-and-redirect-to-faces-action
     * http://www.coderanch.com/t/541659/JSF/java/JSF-redirect-page
     * @throws IOException
     */
    public void redirect() throws IOException {
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        if (this.isUserLogggedIn()) {
            extContext.redirect(extContext.getRequestContextPath() + "/dashboard.xhtml");
        } else {
            extContext.redirect(extContext.getRequestContextPath() + "/login.xhtml");
        }
    }
}