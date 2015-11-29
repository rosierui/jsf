package org.moonwave.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.moonwave.jpa.model.User;
import org.moonwave.util.SHAUtil;

@ManagedBean
//@SessionScoped
public class UserLoginView extends BaseView {
     
    private String username;
    private String password;
	boolean signIn = false;
 
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
//    	Boolean ret = false;
        Object ret = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("isUserLoggedIn");
        return (ret != null) ? true : false; 
    }

    public String login() {
    	
    	
//        RequestContext context = RequestContext.getCurrentInstance();
//        FacesMessage message = null;
//        boolean loggedIn = false;
//
//        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
//            loggedIn = true;
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
//
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//            HttpSession session = (HttpSession) (ec.getSession(false));
//            //Map o = (Map) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userProfile");
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isUserLoggedIn", Boolean.TRUE);
//        } else {
//            loggedIn = false;
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
//        }
//         
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        context.addCallbackParam("loggedIn", loggedIn);
//    	========================================================================
//        return "index.xhtml?redirect=true";//ok
//        return "index.xhtml?faces-redirect=true";//ok
//        return "index?faces-redirect=true";//ok
//        return "/admin/newuser.xhtml?faces-redirect=true";//ok
    	
        EntityManager em = super.getBasebo().getEntityManager();

        // TutorGroup
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isUserLoggedIn", Boolean.TRUE);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", user);
        return "/admin/newuser?faces-redirect=true";//ok
//        return "admin/newuser?faces-redirect=true";//ok
    }
}