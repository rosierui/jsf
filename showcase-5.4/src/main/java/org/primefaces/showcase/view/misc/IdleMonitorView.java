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
package org.primefaces.showcase.view.misc;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.moonwave.util.CookieUtil;

/**
 * Test URLs
 *    /ui/overlay/dialog/basic.xhtml 
 *    /sessionTimeout.xhtml
 *
 */
@ManagedBean
public class IdleMonitorView {

    static int MAX_INACTIVE_INTERVAL = 0;

    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                "No activity.", "You're in idle mode"));
    }

    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Welcome Back", "Back to where you were"));
    }

    public void welcomeListener() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                "Welcome Back",  "Continue your works."));
    }

    /**
     * Get session max inactive interval in seconds
     */
    public int getSessionMaxInactiveInterval() {
        if (MAX_INACTIVE_INTERVAL == 0) {
            MAX_INACTIVE_INTERVAL = FacesContext.getCurrentInstance().getExternalContext().getSessionMaxInactiveInterval();
        }
        System.out.println("IdleMonitorView::getSessionMaxInactiveInterval: " + MAX_INACTIVE_INTERVAL);
        return MAX_INACTIVE_INTERVAL;
    }

    /**
     * Dummy action listener to keep current session live
     */
    public void keepSessionAlive(ActionEvent event) {
        welcomeListener();
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        System.out.println("IdleMonitorView::keepSessionAlive called");
    }

    public void login() throws Exception {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/ui/overlay/dialog/loginDemo.xhtml");
    }

    /**
     * Logout action listener using redirect
     *
     * stackoverflow.com/questions/11277366/what-is-the-difference-between-redirect-and-navigation-forward-and-when-to-use-w
     * stackoverflow.com/questions/3909267/differences-between-action-and-actionlistener
     *
     * @return
     * @throws Exception
     */
    public void logout(ActionEvent event) {
        System.out.println("listener - logout() called");

        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,
            "You Have Logged Out!",
            "Thank you for Moonwave Services"));

        // remove cookies
        CookieUtil.removeCookies();
        System.out.println("remove cookies");

        // invalidate session
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("loggedInUser", null);
        ec.invalidateSession();

        // redirect to logout page
        try {
	        ec.redirect(ec.getRequestContextPath() + "/ui/overlay/dialog/logout.xhtml");
	//      ec.dispatch(ec.getRequestContextPath() + "/ui/overlay/dialog/logout.xhtml"); // forward
        } catch (Exception e) {
        	
        }
    }

    /**
     * Logout action using forward
     *
     * @return
     * @throws Exception
     */
    public String logoutAndForward() {
        System.out.println("Action - logoutAndForward() called");

        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,
            "You Have Logged Out!",
            "Thank you for Moonwave Services"));

        // remove cookies
        CookieUtil.removeCookies();
        System.out.println("remove cookies");

        // invalidate session
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("loggedInUser", null);
        ec.invalidateSession();

        return "/noauth/logout.xhtml"; // or "/ui/overlay/dialog/logout.xhtml"
    }

    /**
     * Logout action using Redirect
     *
     * @return
     * @throws Exception
     */
    public String logoutAndRedirect() {
        System.out.println("Action - logoutAndRedirect() called");

        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,
            "You Have Logged Out!",
            "Thank you for Moonwave Services"));

        // remove cookies
        CookieUtil.removeCookies();
        System.out.println("remove cookies");

        // invalidate session
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("loggedInUser", null);
        ec.invalidateSession();

//      return "/noauth/logout.xhtml?faces-redirect=true";
        return "/ui/overlay/dialog/logout.xhtml?faces-redirect=true";
    }
}
