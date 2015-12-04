package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")

@NamedQueries({
    @NamedQuery(name="Role.findAll",  query="SELECT r FROM Role r"),
    @NamedQuery(name="Role.findById", query="SELECT r FROM Role r WHERE r.id = :id")
})

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private short id;

    private String alias;

    @Column(name="create_time")
    private Timestamp createTime;

    private String name;

    private String privileges;

    @Column(name="update_time")
    private Timestamp updateTime;

    //bi-directional many-to-many association to User
    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public Role() {
    }

    public short getId() {
        return this.id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}