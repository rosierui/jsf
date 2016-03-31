package org.moonwave.jsf.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void addResponseCookie(String name, String value) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.addResponseCookie(name, value, null);
    }

    public static void addResponseCookie(String name, String value,
                                         Map<String, Object> properties) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.addResponseCookie(name, value, properties);
    }

    public static void addSecureResponseCookie(String name, String value) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.addResponseCookie(name, value, getSecureProperties(ec));
    }

    /**
     * Remove a cookie by name
     *
     * @param name the name of a cookie to be removed
     */
    public static void removeCookie(String name) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("Max-Age", 0);
        ec.addResponseCookie(name, "", properties);
    }

    /**
     * Remove all cookies
     */
    public static void removeCookies() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookieMap = ec.getRequestCookieMap();
        Iterator<Object> it = cookieMap.values().iterator();

        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof Cookie) {
                Cookie cookieObj = (Cookie) obj;
                removeCookie(cookieObj.getName());
            }
        }
    }

    private static Map<String, Object> getSecureProperties(ExternalContext externalContext) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("secure", ((HttpServletRequest) externalContext.getRequest()).isSecure()); // true for https
        properties.put("httpOnly", Boolean.TRUE);
        return properties; 
    }
}
