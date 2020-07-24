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

   public void forwardToEmailPage(ComponentSystemEvent cse) {
       FacesContext fc = FacesContext.getCurrentInstance();
       String viewId = fc.getViewRoot().getViewId();
       fc.getApplication().getNavigationHandler().handleNavigation(
       fc, 
       null,
       "/email?faces-redirect=true" );
   }
}