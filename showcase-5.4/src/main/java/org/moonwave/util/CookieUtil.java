package org.moonwave.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.sun.faces.context.ExternalContextImpl 
 *     private enum ALLOWABLE_COOKIE_PROPERTIES {
 *         domain,
 *         maxAge,
 *         path,
 *         secure,
 *         httpOnly
 *    }
 */
public class CookieUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void addResponseCookie(String name, String value, String path) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) ec.getResponse();

        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);

        response.addCookie(cookie);
    }

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

    public static void addSecureResponseCookie(String name, String value, String path) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> properties = getSecureProperties(ec);
        properties.put("path", path);
        ec.addResponseCookie(name, value, properties);
    }

    /**
     * Remove all cookies
     *
     * http://stackoverflow.com/questions/890935/how-do-you-remove-a-cookie-in-a-java-servlet
     */
    public static void removeCookies() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        HttpServletResponse response = (HttpServletResponse) ec.getResponse();
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            cookie = new Cookie(cookies[i].getName(), cookies[i].getValue());
            cookie.setMaxAge(0);
            cookie.setValue("");
            cookie.setPath((cookies[i].getPath() != null) ? cookies[i].getPath() : "/");

            if (cookies[i].getDomain() != null) {
                cookie.setDomain(cookies[i].getDomain());
            }
            response.addCookie(cookie);
        }

        removeOtherCookies();
    }

    /**
     * Remove a cookie by name and path
     *
     * @param name the name of a cookie to be removed
     * @param path cookie path
     */
    public static void removeCookie(String name, String path) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("maxAge", 0);
        properties.put("path", path);
        ec.addResponseCookie(name, "", properties);
    }

    /**
     * Remove all cookies that not being sent back to server side
     * This can be done in a property file
     */
    private static void removeOtherCookies() {
        removeCookie("JSESSIONID", "/");
//      removeCookie("cookie2", "/path");
    }

    /**
     * Remove a cookie by name
     *
     * @param name the name of a cookie to be removed
     */
    private static void removeCookie(String name) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("maxAge", 0);
        ec.addResponseCookie(name, "", properties);
    }


    private static Map<String, Object> getSecureProperties(ExternalContext externalContext) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("secure", ((HttpServletRequest) externalContext.getRequest()).isSecure()); // true for https
        properties.put("httpOnly", Boolean.TRUE);
        return properties; 
    }
}
