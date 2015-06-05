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

package org.moonwave.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 * http://www.smipple.net/snippet/Forero/JSF%20%3A%3A%20How%20to%20redirect%20to%20login%20page%20if%20not%20logged%20on
 */
@ManagedBean
@SessionScoped
public class MainMB {

   public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent cse) {
       FacesContext fc = FacesContext.getCurrentInstance();
       String viewId = fc.getViewRoot().getViewId();
       if (!fc.getExternalContext().getSessionMap().containsKey("currentUser") && 
           !viewId.startsWith("/login") ) {
             fc.getApplication().getNavigationHandler().handleNavigation(
             fc, 
             null,
             "/login?faces-redirect=true" );
       }
   }
}