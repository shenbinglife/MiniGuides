package io.github.shenbinglife.springboot2.layui.dao;

import io.github.shenbinglife.springboot2.layui.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
}
