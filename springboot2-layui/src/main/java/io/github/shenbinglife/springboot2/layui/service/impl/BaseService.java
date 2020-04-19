package io.github.shenbinglife.springboot2.layui.service.impl;

import io.github.shenbinglife.springboot2.layui.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseService<T, DAO extends JpaRepository<T, Long>> implements IService<T> {

    @Autowired
    protected DAO baseDao;


    @Override
    public List<T> list() {
        return baseDao.findAll();
    }

    @Override
    public Page<T> page(PageRequest pageRequest) {
        return baseDao.findAll(pageRequest);
    }

    @Override
    public T getById(Long id) {
        return baseDao.findById(id).orElse(null);
    }

    @Override
    public void update(T t) {
        baseDao.save(t);
    }

    @Override
    public void delete(T t) {
        baseDao.delete(t);
    }

    @Override
    public void deleteById(Long id) {
        baseDao.deleteById(id);
    }
}
