package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")

@NamedQueries({
    @NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u"),
    @NamedQuery(name="UserRole.findByRole", query = "SELECT u FROM UserRole u WHERE u.roleId = :roleId")
})

public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name="create_time")
    private Timestamp createTime;

    private String privileges;

    @Column(name="user_id")
    private int userId;

    @Column(name="role_id")
    private short roleId;

    @Column(name="update_time")
    private Timestamp updateTime;

    public UserRole() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public short getRoleId() {
        return this.roleId;
    }

    public void setRoleId(short roleId) {
        this.roleId = roleId;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}