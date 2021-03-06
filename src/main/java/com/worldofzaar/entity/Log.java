package com.worldofzaar.entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 09.10.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "\"Logs\"")
public class Log {
    @Id
    @SequenceGenerator(name = "log_seq", sequenceName = "\"Logs_logId_seq\"", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_seq")
    @Column(name = "\"logId\"")
    private Integer logId;
    @Column(name = "\"logInfo\"")
    private String log;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
