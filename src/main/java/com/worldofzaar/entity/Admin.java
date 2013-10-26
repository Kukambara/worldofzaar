package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 13.10.13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Admins\"")
public class Admin {
    @Id
    @SequenceGenerator(name = "admin_seq", sequenceName = "\"Admins_adminId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
    @Column(name = "\"adminId\"")
    private Integer adminId;
    @OneToOne
    @JoinColumn(name = "\"webUserId\"")
    private WebUser webUser;
    @Column(name = "\"isApproved\"")
    private Boolean isApproved;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUserId) {
        this.webUser = webUserId;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
