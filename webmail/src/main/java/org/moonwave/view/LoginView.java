package org.moonwave.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.moonwave.jpa.query.EmployeeNativeQuery;

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
        System.out.println("LoginController::setPassword value: " + password);
        
        EmployeeNativeQuery test = new EmployeeNativeQuery();
        try {
            test.setUp();
            test.query();
            test.tearDown();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        this.password = password;
    }

}