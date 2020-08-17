package io.github.shenbinglife.springboot2.layui;

import io.github.shenbinglife.springboot2.layui.entity.BaseEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityScan(basePackageClasses = BaseEntity.class)
@EnableJpaAuditing
@SpringBootApplication
public class Springboot2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2DemoApplication.class, args);
    }


}
