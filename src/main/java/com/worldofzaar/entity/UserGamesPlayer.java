package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 08.10.13
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"UserGamesPlayers\"")
public class UserGamesPlayer {
    @Id
    @SequenceGenerator(name = "userGamesPlayer_seq", sequenceName = "\"UserGamesPlayers_userGamesPlayerId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGamesPlayer_seq")
    @Column(name = "\"userGamesPlayerId\"")
    private Integer userGamesPlayerId;
    @ManyToOne
    @JoinColumn(name = "\"myGameId\"")
    private MyGame myGame;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    private User user;
}
