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

    public static void removeCookies() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookieMap = ec.getRequestCookieMap();
        Iterator<Object> it = cookieMap.values().iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof Cookie) {
                Cookie cookie = (Cookie) obj;
                cookie.setValue("");
                cookie.setMaxAge(0);
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
