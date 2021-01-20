package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

public interface Rule {
    <T> T findById(Integer id);
    List<?> findAll(Integer userId);
    Integer insert(Object add);
    Integer update(Object update);
    Integer delete(Integer id);
}
