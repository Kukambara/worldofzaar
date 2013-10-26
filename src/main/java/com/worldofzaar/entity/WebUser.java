package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.10.13
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"WebUsers\"")
public class WebUser {
    @Id
    @SequenceGenerator(name="webUser_seq", sequenceName="\"WebUsers_webUserId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="webUser_seq")
    @Column(name = "\"webUserId\"")
    private Integer webUserId;
    @Column(name = "\"webUserEmail\"")
    private String webUserEmail;
    @Column(name = "\"webUserPass\"")
    private String webUserPass;
    @OneToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @Column(name = "\"isApproved\"")
    private Boolean isApproved;

    public Integer getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(Integer webUserId) {
        this.webUserId = webUserId;
    }

    public String getWebUserEmail() {
        return webUserEmail;
    }

    public void setWebUserEmail(String webUserEmail) {
        this.webUserEmail = webUserEmail;
    }

    public String getWebUserPass() {
        return webUserPass;
    }

    public void setWebUserPass(String webUserPass) {
        this.webUserPass = webUserPass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
