package io.github.shenbinglife.springboot2.layui.service.impl;

import io.github.shenbinglife.springboot2.layui.dao.UserDao;
import io.github.shenbinglife.springboot2.layui.entity.Org;
import io.github.shenbinglife.springboot2.layui.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<User, UserDao> {

    public Page<User> page(String name, String mobilePhone, String orgName, int pageNo, int pageSize) {
        Sort sorter = Sort.by(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sorter);
        Page<User> pageUsers = baseDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(name)) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }
                if (!StringUtils.isEmpty(mobilePhone)) {
                    predicates.add(criteriaBuilder.like(root.get("mobileName"), "%" + mobilePhone + "%"));
                }
                if (!StringUtils.isEmpty(orgName)) {
                    Join<User, Org> orgJoin = root.join("orgs", JoinType.LEFT);
                    predicates.add(criteriaBuilder.like(orgJoin.get("name"), "%" + orgName + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        }, pageRequest);
        return pageUsers;
    }
}
