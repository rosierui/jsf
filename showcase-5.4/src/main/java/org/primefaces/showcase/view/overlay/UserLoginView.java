/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.overlay;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.moonwave.jsf.util.CookieUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.showcase.domain.User;

/**
 * http://localhost/showcase-5.4/ui/overlay/dialog/loginDemo.xhtml
 *
 */
@ManagedBean
public class UserLoginView {
    
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

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);

        // enable session
        // http://stackoverflow.com/questions/5505328/how-can-i-create-a-new-session-with-a-new-user-login-on-the-application
        User user = new User();
        user.setFirstname(username);
        CookieUtil.addResponseCookie("test_1", "abc_012");
        CookieUtil.addResponseCookie("test_11", "asi es el amor");
        CookieUtil.addSecureResponseCookie("test_11_sec", "Mantiene su estado de gracia");
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
    }

    /**
     * Dummy method to keep current session live
     */
    public void keepSessionAlive() {
    }
}
