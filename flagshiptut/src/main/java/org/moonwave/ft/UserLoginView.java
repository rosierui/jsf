package org.moonwave.ft;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
 
@ManagedBean
//@SessionScoped
public class UserLoginView {
     
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
   
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) (ec.getSession(false));
            //Map o = (Map) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userProfile");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isUserLoggedIn", Boolean.TRUE);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }   
}