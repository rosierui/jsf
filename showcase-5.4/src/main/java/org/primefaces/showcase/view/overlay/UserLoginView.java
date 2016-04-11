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

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.moonwave.util.CookieUtil;
import org.moonwave.util.JSFUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.showcase.domain.User;


/**
 * http://localhost/showcase-5.4/ui/overlay/dialog/loginDemo.xhtml
 *
 * See also IdleMonitorView
 *  
 */
@ManagedBean
@SessionScoped
public class UserLoginView {

    private String username;
    
    private String password;

    // helper fields
    private String pagename;

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

    /**
     * Login and redirect to a different page
     *
     */
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
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Object session = ec.getSession(false);

        // http://stackoverflow.com/questions/11206817/how-to-detect-session-has-been-invalidated-in-jsf-2
        boolean valid = true;
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()) {
            valid = false;
            // Session has been invalidated during the previous request.
        }

        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        User loggedInUser = new User();
        loggedInUser.setFirstname(username);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", loggedInUser);

        session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session = FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        // test code here - to remove
        CookieUtil.addResponseCookie("cancion-1", "Chino y Nacho - Regalame Un Muack", "/");
        CookieUtil.addResponseCookie("cancion-2", "Mana - Labios Compartidos", "/");
        CookieUtil.addSecureResponseCookie("cancion-3", "Thalia - Te Perdiste Mi Amor", "/");

        // redirect to a page
        try {
            ec.redirect(ec.getRequestContextPath() + "/ui/overlay/dialog/basic.xhtml"); // /ui/file/upload/basic.xhtml
//            ec.dispatch(ec.getRequestContextPath() + "/ui/overlay/dialog/basic.xhtml"); // java.lang.IllegalStateException: Component ID j_idt102 has already been found in the view.
//          ec.dispatch("/ui/overlay/dialog/basic.xhtml");
        } catch (Exception e) {
        }
    }

    /**
     * Login and forward to a different page
     * 
     */
    public String loginAndForward() {
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
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Object session = ec.getSession(false);

        // http://stackoverflow.com/questions/11206817/how-to-detect-session-has-been-invalidated-in-jsf-2
        boolean valid = true;
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()) {
            valid = false;
            // Session has been invalidated during the previous request.
        }

        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        User loggedInUser = new User();
        loggedInUser.setFirstname(username);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", loggedInUser);

        session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session = FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        // test code here - to remove
        // default path /showcase-5.4/ui/overlay/dialog, but the path is null 
        // when sessionTimeout is activated since sessionTimeout.xml does not locate at the same path
        CookieUtil.addResponseCookie("abc_1", "abc_012", "/");
        CookieUtil.addResponseCookie("abc_11", "asi es el amor", "/");
        CookieUtil.addSecureResponseCookie("abc_11_sec", "Mantiene su estado de gracia", "/");

        // forward to a page
//        return "basic.xhtml"; // not working
        return "/ui/overlay/dialog/basic.xhtml"; // /ui/file/upload/basic.xhtml
    }

    private boolean isLogin() {
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Object obj1 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        Object obj = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (obj != null);
    }

    public Boolean isUserLoggedIn() {
        Object user = JSFUtil.getSessionMap().get("loggedInUser");
        return (user != null) ? true : false; 
    }

    // ========================================================== helper methods

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

}
