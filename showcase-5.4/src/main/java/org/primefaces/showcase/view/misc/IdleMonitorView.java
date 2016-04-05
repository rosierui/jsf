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

    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "No activity.", "What are you doing over there?"));
    }

    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Welcome Back", "Well, that's a long coffee break!"));
    }

    public void welcomeListener() {
        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_WARN, "Welcome Back",
            "Continue your works."));
    }

    /**
     * Get session max inactive interval in seconds
     */
    public int getSessionMaxInactiveInterval() {
        int maxInactiveInterval = FacesContext.getCurrentInstance().getExternalContext().getSessionMaxInactiveInterval();
        System.out.println("IdleMonitorView::getSessionMaxInactiveInterval: " + maxInactiveInterval); // always return 1800

        // test code below
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            maxInactiveInterval = session.getMaxInactiveInterval(); // always return 1800
            System.out.println("IdleMonitorView::getSessionMaxInactiveInterval(2): " + maxInactiveInterval);
        }

        return maxInactiveInterval;
    }

    /**
     * Dummy method to keep current session live
     */
    public void keepSessionAlive() {
        welcomeListener();
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        int maxInactiveInterval = FacesContext.getCurrentInstance().getExternalContext().getSessionMaxInactiveInterval();
        System.out.println("IdleMonitorView::keepSessionAlive called");
        System.out.println("maxInactiveInterval: " + maxInactiveInterval);
    }

    public void logout() throws Exception {
        System.out.println("UserLoginView::logout called");

        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,
            "You Have Logged Out!",
            "Thank you for using abc Online Financial Services"));

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("loggedInUser", null);
        ec.invalidateSession();
        // or
        HttpSession session = (HttpSession) ec.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // remove cookies
        CookieUtil.removeCookies();

        // redirect to logout page
        ec.redirect(ec.getRequestContextPath() + "/ui/overlay/dialog/logout.xhtml");
//      ec.dispatch(ec.getRequestContextPath() + "/ui/overlay/dialog/logout.xhtml");
    }

}
