package com.patentdata.dao;

import java.util.List;

public interface GenericDAO<T> {
    
    public void add(T t);
    public void update(T t);
    public void remove(T t);
    public T findById(Class<T> clazz, Object id);
    public List<T> queryAll(Class<T> clazz);

}
