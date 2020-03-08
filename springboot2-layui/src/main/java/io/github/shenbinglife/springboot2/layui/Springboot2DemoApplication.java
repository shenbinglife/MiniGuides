package io.github.shenbinglife.springboot2.layui;

import com.github.jsonzou.jmockdata.JMockData;
import io.github.shenbinglife.springboot2.layui.dao.OrgDao;
import io.github.shenbinglife.springboot2.layui.dao.UserDao;
import io.github.shenbinglife.springboot2.layui.entity.BaseEntity;
import io.github.shenbinglife.springboot2.layui.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackageClasses = BaseEntity.class)
@SpringBootApplication
public class Springboot2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2DemoApplication.class, args);

    }



}
