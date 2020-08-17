package io.github.shenbinglife.springboot2.layui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IService<T> {

    List<T> list();

    Page<T> page(PageRequest pageRequest);

    T getById(Long id);

    void update(T t);

    void delete(T t);

    void deleteById(Long id);

}
