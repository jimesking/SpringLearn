package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domian.LoginLog;
import com.smart.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2017/9/11.
 */
public class UserService {
    private UserDao userDao;

    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName,String password){
        int matchCount=userDao.getMatchCount(userName,password);
        return matchCount>0;
    }

    public User findUserByName(String userName){
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog=new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());

        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
