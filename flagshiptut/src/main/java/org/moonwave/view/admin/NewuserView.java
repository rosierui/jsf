package org.moonwave.view.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.moonwave.jpa.model.User;
import org.moonwave.util.RandomUtil;
import org.moonwave.util.SHAUtil;
import org.moonwave.view.BaseView;

/**
 * NewuserView
 * @author moonwave
 *
 */
@ManagedBean
public class NewuserView extends BaseView {

    User user;
    String password2;
    List<String> timezones;

    @PostConstruct
    public void init() {
        timezones = new ArrayList<String>();

        String[] availableTZs = TimeZone.getAvailableIDs();
        List<String> tzs = Arrays.asList(availableTZs);
        Collections.sort(tzs);
        timezones.add("");
        timezones.addAll(tzs);
        if (user == null) {
            user = new User();
        }
        user.setTimezone("America/Phoenix"); // get from properties
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public String save() {
        StringBuffer sb = new StringBuffer();
        if ((user.getPassword() != null) && !user.getPassword().equals(this.password2)) {
            super.error("Password not equal.");
            return "";
        }

        try {
            user.setPassword(SHAUtil.encryptPassword(user.getPassword()));
            user.setCreateTime(super.getSqlTimestamp());
            super.getBasebo().persist(user);
            super.info("Succesfully created new record");
        } catch (Exception e) {
            
        }
        return "";
    }
}
