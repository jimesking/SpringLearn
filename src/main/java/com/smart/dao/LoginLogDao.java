package com.smart.dao;

import com.smart.domian.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/9/8.
 */
@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final static String INSERT_LOGIN_LOG_SQL="insert into t_login_log(user_id,ip,login_datetime)";

    public void insertLoginLog(LoginLog loginLog){
        Object[] args={loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,args);
    }
}
