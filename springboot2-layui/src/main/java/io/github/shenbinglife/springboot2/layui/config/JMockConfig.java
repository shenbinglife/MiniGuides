package io.github.shenbinglife.springboot2.layui.config;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
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
    public static MockConfig mockConfig = new MockConfig();
    static {
        mockConfig.globalConfig().excludes("users", "orgs");
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgDao orgDao;

    @EventListener(ApplicationReadyEvent.class)
    public void mock() {
        for (int i = 0; i < 5; i++) {
            User user = JMockData.mock(User.class, mockConfig);
            userDao.save(user);
        }

        for (int i = 0; i < 2; i++) {
            Org org = JMockData.mock(Org.class, mockConfig);
            org.setName("org" + i);
            org.setParentId(-1L);
            orgDao.save(org);
        }
    }
}
