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
import javax.servlet.http.HttpSession;

import org.moonwave.util.CookieUtil;

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
     * Dummy method to keep current session live
     */
    public void keepSessionAlive() {
        welcomeListener();
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        System.out.println("IdleMonitorView::keepSessionAlive called");
    }

    public void login() throws Exception {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/ui/overlay/dialog/loginDemo.xhtml");
    }

    public void logout() throws Exception {
        System.out.println("logout() called");

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
        ec.redirect(ec.getRequestContextPath() + "/ui/overlay/dialog/logout.xhtml");
//      ec.dispatch(ec.getRequestContextPath() + "/ui/overlay/dialog/logout.xhtml");
    }

}
