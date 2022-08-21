package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
public class MailTest {

    @Autowired
    private MailClient client;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendMail(){

        client.sendMail("temp123456000@outlook.com", "测试邮件", "<h2>你好啊</h2>");

    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username", "sunday");
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        client.sendMail("temp123456000@outlook.com", "测试邮件", content);
    }

}
