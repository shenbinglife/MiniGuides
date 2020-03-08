package io.github.shenbinglife.springboot2.layui.config;

import com.github.jsonzou.jmockdata.JMockData;
import io.github.shenbinglife.springboot2.layui.dao.OrgDao;
import io.github.shenbinglife.springboot2.layui.dao.UserDao;
import io.github.shenbinglife.springboot2.layui.entity.Org;
import io.github.shenbinglife.springboot2.layui.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class JMockConfig {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgDao orgDao;

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setAccount("xxx" + i);
            user.setAge(13);
            userDao.save(user);
        }

        for (int i = 0; i < 2; i++) {
            Org org = new Org();
            org.setName("org" + i);
            org.setParentId(-1L);
            orgDao.save(org);
        }
    }
}
