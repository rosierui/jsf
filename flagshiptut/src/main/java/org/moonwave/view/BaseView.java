package org.moonwave.view;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.moonwave.jpa.bo.BaseBO;

/**
 * BaseView
 *
 * @author moonwave
 *
 */
@ManagedBean
public class BaseView implements Serializable {

    // load message and labels by locale

    private static final long serialVersionUID = 5631028692861147930L;

    BaseBO basebo = new BaseBO();

    public BaseBO getBasebo() {
        return basebo;
    }

    public java.sql.Timestamp getSqlTimestamp() {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public void info(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
    }

    public void info(String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
    }

    public void warn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", msg));
    }

    public void error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", msg));
    }

    public void fatal(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", msg));
    }
}
