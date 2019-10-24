package com.jones.service;

import java.util.List;

public interface BaseService<T> {

    /**
     * description 有局限性
     *
     * @param t
     * @return void
     */
    public void add(T t);

    /**
     * description 改进添加方法
     *
     * @param fieldNames
     * @param fieldValues
     * @return void
     */
    public void addForNotMatch(Object[] fieldNames,Object[] fieldValues);

    public void delete(int id);

    public void update(T t);

    public T selectOne(int id);

    public List<T> selectAll();
}
