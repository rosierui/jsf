/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2015 Jonathan Luo
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 */

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