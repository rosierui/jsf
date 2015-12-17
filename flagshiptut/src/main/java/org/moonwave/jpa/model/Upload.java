package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.io.FilenameUtils;
import org.moonwave.util.FileUtil;

import java.sql.Timestamp;

/**
 * The persistent class for the uploads database table.
 * 
 */
@Entity
@Table(name="upload")
@NamedQuery(name="Upload.findAll", query="SELECT u FROM Upload u")
public class Upload implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name="user_id")
    private int userId;

    @Column(name="tutor_group_id")
    private short tutorGroupId;

    @Column(name="announcement_id")
    private int announcementId;

    @Column(name="group_post_id")
    private int groupPostId;

    private String description;

    private String filetype;

    private String filepath;

    @Column(name="create_time")
    private Timestamp createTime;

    @Transient
    private String tag;

    public Upload() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public short getTutorGroupId() {
        return tutorGroupId;
    }

    public void setTutorGroupId(short tutorGroupId) {
        this.tutorGroupId = tutorGroupId;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public int getGroupPostId() {
        return groupPostId;
    }

    public void setGroupPostId(int groupPostId) {
        this.groupPostId = groupPostId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof Upload)) {
            return false;
        }
        Upload other = (Upload) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id= ").append(id);
        sb.append(",userId= ").append(userId);
        sb.append(",fileType= ").append(filetype);
        sb.append(",filepath= ").append(filepath);
        return sb.toString();
    }

    // ========================================================== Helper methods
    public String getFilename() {
        return FilenameUtils.getName(filepath);
    }
}