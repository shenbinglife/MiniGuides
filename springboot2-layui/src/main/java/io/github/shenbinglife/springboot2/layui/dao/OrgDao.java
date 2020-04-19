package io.github.shenbinglife.springboot2.layui.dao;

import io.github.shenbinglife.springboot2.layui.entity.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgDao extends JpaRepository<Org, Long>, JpaSpecificationExecutor<Org> {
    List<Org> findByParentId(Long parentId);
}
