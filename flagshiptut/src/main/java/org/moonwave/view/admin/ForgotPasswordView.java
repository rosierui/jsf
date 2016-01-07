package org.moonwave.view.admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;

import org.moonwave.jpa.bo.UserBO;
import org.moonwave.jpa.model.User;
import org.moonwave.util.DateUtil;
import org.moonwave.util.JSFUtil;
import org.moonwave.util.SHAUtil;
import org.moonwave.view.BaseView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ForgotPasswordView
 *
 * @author moonwave
 *
 */
@ManagedBean
public class ForgotPasswordView extends BaseView {

    private static final long serialVersionUID = -6980002954656894189L;

    static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordView.class);

    User user;
    String email;
    String email2;

    @PostConstruct
    public void init() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String save() {
        if ((email != null) && !email.equals(email2)) {
            super.error("Email not equal");
            return "";
        }
        user = new UserBO().findByEmail(email);
        if (user == null) {
            super.error("Invalid email address");
            return "";
        }
        String url = null;
        try {
            String actionCode = SHAUtil.sha1(email + DateUtil.getToday().toString());
            user.setActionCode(actionCode);
            super.getBasebo().update(user);
            // TODO - send email to user
            ExternalContext ec = JSFUtil.getExternalContext();
            url = JSFUtil.getApplicationURL() + "/admin/resetPassword.xhtml?actionCode=" + actionCode;
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage());
            super.error("An error occurred while saving data");
        }
        return "";
    }
}
