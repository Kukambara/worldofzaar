package com.worldofzaar.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 29.09.13
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Auctions\"")
public class Auction {

    @Id
    @GeneratedValue
    @Column(name = "\"auctionId\"")
    private Integer auctionId;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
    @Column(name = "\"startDateTime\"")
    private Date startDate;
    @Column(name = "\"finishDateTime\"")
    private Date finishDate;
    @Column(name = "\"price\"")
    private Integer price;
    @Column(name = "\"donatePrice\"")
    private Integer donatePrice;
    @Column(name = "\"lastPriceBet\"")
    private Integer lastPrice;
    @Column(name = "\"lastDonateBet\"")
    private Integer lastDonatePrice;
    @ManyToOne
    @JoinColumn(name = "\"lastUserId\"")
    private User lastUser;
    @ManyToOne
    @JoinColumn(name = "\"userCardId\"")
    private UserCard userCard;

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDonatePrice() {
        return donatePrice;
    }

    public void setDonatePrice(Integer donatePrice) {
        this.donatePrice = donatePrice;
    }

    public Integer getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Integer lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Integer getLastDonatePrice() {
        return lastDonatePrice;
    }

    public void setLastDonatePrice(Integer lastDonatePrice) {
        this.lastDonatePrice = lastDonatePrice;
    }

    public User getLastUser() {
        return lastUser;
    }

    public void setLastUser(User lastUser) {
        this.lastUser = lastUser;
    }

    public UserCard getUserCard() {
        return userCard;
    }

    public void setUserCard(UserCard userCard) {
        this.userCard = userCard;
    }
}
