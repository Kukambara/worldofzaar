package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Messages\"")
public class Message {
    @Id
    @GeneratedValue
    @Column(name = "\"messageId\"")
    private Integer messageId;
    @ManyToOne
    @JoinColumn(name = "\"fromUserId\"")
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "\"toUserId\"")
    private User toUser;
    @Column(name = "\"messageText\"")
    private String messageText;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}