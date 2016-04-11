package org.primefaces.showcase.view.misc;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.moonwave.util.CookieUtil;

@ManagedBean
public class LogoutView {

    @PostConstruct
    public void init() {
        CookieUtil.removeCookies();
        System.out.println("remove cookies");
    }

    /**
	 * Return dummy message
	 * @return
	 */
    public String getMessage() {
        return "";
    }
}
