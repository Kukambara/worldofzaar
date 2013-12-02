package com.worldofzaar.service;

import com.worldofzaar.dao.LogDao;
import com.worldofzaar.entity.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 12.10.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class LogService {
    public Log createLog() {
        LogDao logDao = new LogDao();
        Log log = new Log();
        logDao.add(log);
        return log;
    }
}
