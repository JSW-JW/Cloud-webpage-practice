package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

public interface Rule {

    Object findById(Integer id);

    List<Object> findAll(Integer userId);

    Integer insert(Object object);

    void update(Object object);

    void delete(Integer id);
}
