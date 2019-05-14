package com.jcohy.moead.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 14:56 2019/5/6
 * Email: jia_chao23@126.com
 * ClassName: Reply
 * Description:
 **/
@Entity
@Table(name = "reply")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "content")
    private String content;

    @Column(name = "time")
    private String time;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Reply{");
        sb.append("id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", questionId='").append(questionId).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
