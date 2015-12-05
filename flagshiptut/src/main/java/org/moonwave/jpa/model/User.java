package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")

@NamedQueries({
    @NamedQuery(name="User.findAll",   query="SELECT u FROM User u"),
    @NamedQuery(name="User.findAllGenericUsers", query="SELECT u FROM User u where u.genericUser = true"),
    @NamedQuery(name="User.findById",  query="SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name="User.findByLoginId",  query="SELECT u FROM User u WHERE u.loginId = :loginId"),
    @NamedQuery(name="User.findByEmail",  query="SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name="User.findInIds", query="SELECT u FROM User u WHERE u.id in (:ids)")
})

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private Boolean active;

    private String answer;

    @Column(name="create_time")
    private Timestamp createTime;

    private String email;

    @Column(name="first_name")
    private String firstName;

    private String hint;

    private String timezone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_attempt_ts")
    private Date lastAttemptTs;

    @Column(name="last_login_ip")
    private String lastLoginIp;

    @Column(name="last_name")
    private String lastName;

    @Column(name="login_attempt")
    private short loginAttempt;

    @Column(name="login_id")
    private String loginId;

    private String password;

    private String phone;

    @Column(name="generic_user")
    private Boolean genericUser;

    @Column(name="update_time")
    private Timestamp updateTime;

    //bi-directional many-to-many association to Role
    @ManyToMany
    @JoinTable(
        name="user_role"
        , joinColumns={
            @JoinColumn(name="user_id")
            }
        , inverseJoinColumns={
            @JoinColumn(name="role_id")
            }
        )
    private List<Role> roles;

    //bi-directional many-to-many association to TutorGroup
    @ManyToMany
    @JoinTable(
        name="user_tutor_group"
        , joinColumns={
            @JoinColumn(name="user_id")
            }
        , inverseJoinColumns={
            @JoinColumn(name="tutor_group_id")
            }
        )
    private List<TutorGroup> tutorGroups;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getLastAttemptTs() {
        return this.lastAttemptTs;
    }

    public void setLastAttemptTs(Date lastAttemptTs) {
        this.lastAttemptTs = lastAttemptTs;
    }

    public String getLastLoginIp() {
        return this.lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public short getLoginAttempt() {
        return this.loginAttempt;
    }

    public void setLoginAttempt(short loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public String getLoginId() {
        return this.loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isGenericUser() {
        return genericUser;
    }

    public void setGenericUser(Boolean genericUser) {
        this.genericUser = genericUser;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<TutorGroup> getTutorGroups() {
        return this.tutorGroups;
    }

    public void setTutorGroups(List<TutorGroup> tutorGroups) {
        this.tutorGroups = tutorGroups;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id= ").append(id);
        sb.append(",firstname= ").append(firstName);
        sb.append(",lastname= ").append(lastName);
        sb.append(",loginid= ").append(loginId);
        sb.append(",timezone= ").append(timezone);
        return sb.toString();
    }

    // ========================================================== Helper methods

    public void clearFields() {
        this.firstName = null;
        this.lastName = null;
        this.loginId = null;
        this.password = null;
        this.hint = null;
    }

}