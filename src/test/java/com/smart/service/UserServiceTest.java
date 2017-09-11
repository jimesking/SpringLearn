package com.smart.service;


import com.smart.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

//import static org.junit.Assert.assertTrue;

/**
 * Created by admin on 2017/9/11.
 */
@ContextConfiguration("classpath*:/smart-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Test
    public void hasMatchUser(){
        boolean b1= userService.hasMatchUser("admin","123456");
        boolean b2= userService.hasMatchUser("admin","123456");
        assertTrue(b1);
        assertTrue(b2);
    }
    @Test
    public void findUserByUserName(){
        User user=userService.findUserByName("admin");
        assertEquals(user.getUserName(),"admin");
    }
}
