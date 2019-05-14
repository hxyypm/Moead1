package com.jcohy.moead.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 19:42 2019/5/7
 * Email: jia_chao23@126.com
 * ClassName: Algorithmic
 * Description:
 **/
@Entity
@Table(name = "algorithmic")
public class Algorithmic {

    private static final long serialVersionUID = 1L;
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "update_date")
    private String updateDate;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
