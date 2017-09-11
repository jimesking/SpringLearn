package com.smart.dao;

import com.smart.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by admin on 2017/9/8.
 */
@Repository
public class UserDao {
    private final static String MATCH_COUNT_SQL="select count(*) from t_user where user_name=? and password=?";
    private final static String UPDATE_LOGIN_INFO_SQL="update t_user set laset_visit=?,last_ip=?,credits=? where user_id=?";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByUserName(final String userName){
        final User user= new User();
        jdbcTemplate.query(MATCH_COUNT_SQL,new Object[] {userName},new RowCallbackHandler(){
            public void processRow(ResultSet rs) throws SQLException {
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(rs.getInt(rs.getInt("credits")));
            }
        });
        return user;
    }
    public int getMatchCount(String userName,String password){
        return  jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},Integer.class);
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}
