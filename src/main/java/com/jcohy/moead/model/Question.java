package com.jcohy.moead.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description  :管理员表
 */
@Entity
@Table(name = "question")
public class Question implements Serializable{

    private static final long serialVersionUID = 1L;
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //名字
    @Column(name = "name")
    private String name;
    //密码
    @Column(name = "description")
    private String description;
    //电话
    @Column(name = "user_id")
    private Integer userId;
    //
    @Column(name = "type")
    private Integer type;

    @Column(name = "time")
    private String time;

    @Transient
    private List<Reply> replies ;

    @Transient
    private String username ;

    @Transient
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Question{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", type=").append(type);
        sb.append(", replies=").append(replies);
        sb.append('}');
        return sb.toString();
    }
}

