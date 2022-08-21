package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest

class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;


    @Test
    public void testSelectUser() {

        User user = userMapper.selectById(101);
        System.out.println(user);

    }

    @Test
    public void testInsertUser(){

        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Timestamp(new Date().getTime()));

        int row = userMapper.insertUser(user);
        System.out.println(row);
        System.out.println(user);

    }

    @Test
    public void testSelectPost(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        System.out.println(discussPosts);
    }

    @Test
    public void testInsertTicket(){

        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(101);;
        ticket.setTicket(CommunityUtil.generateUUID());
        ticket.setExpired(new Date());
        ticket.setStatus(0);
        loginTicketMapper.insertLoginTicket(ticket);
    }

    @Test
    public void testSelectTicketByTicket(){
        LoginTicket ticket = loginTicketMapper.selectByTicket("36c59a73e53b4021992a6522020f7bfd");
        System.out.println(ticket);
    }

    @Test
    public void testUpdateTicketStatus(){
        loginTicketMapper.updateStatus("36c59a73e53b4021992a6522020f7bfd", 1);
        LoginTicket ticket = loginTicketMapper.selectByTicket("36c59a73e53b4021992a6522020f7bfd");
        System.out.println(ticket);

    }

}
