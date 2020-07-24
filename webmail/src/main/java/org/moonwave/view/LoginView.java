package org.moonwave.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.moonwave.jpa.query.SignInNativeQuery;

@ManagedBean
@SessionScoped
public class LoginView {

    private String username;
    private String password;

    public String getUsername() {
        System.out.println("LoginController::getUsername value: " + username);
        return username;
    }

    public void setUsername(String username) {
        System.out.println("LoginController::setUsername value: " + username);
        this.username = username;
    }

    public String getPassword() {
        System.out.println("LoginController::getPassword value: " + password);
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void signIn(ActionEvent e) {
        SignInNativeQuery query = new SignInNativeQuery();
        try {
            boolean signInSucess = query.signIn(username, password);
            if (signInSucess) {
               FacesContext fc = FacesContext.getCurrentInstance();
               fc.getExternalContext().getSessionMap().put("currentUser", "signed in");
               fc.getApplication().getNavigationHandler().handleNavigation(
                       fc, null, "/email?faces-redirect=true" );
            } else {
                // uername or password is incorrect
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Username or passwoerd is incorrect, try again"));
            }
            
        } catch (Exception e1) {
        }
    }
}