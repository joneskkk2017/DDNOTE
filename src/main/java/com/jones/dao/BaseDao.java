package com.jones.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * description
 *
 * @param
 * @return
 */
public interface BaseDao<T> {


    /**
     * description 曾
     *
     * @param tableName 表名
     * @param objects   参数
     * @return void
     */
    public void add(@Param("tableName") String tableName, @Param("objects") Object[] objects);

    /**
     * @Description TODO 删
     * @Param [tableName 表名, id 序号]
     * @Return void
     * @Date 2019/9/1 21:41
     * @Author 61044
     */
    public void delete(@Param("tableName") String tableName, @Param("id") Integer id);

    /**
     * @Description TODO 改
     * @Param [tableName 表名, id 序号, objects 参数]
     * @Return void
     * @Date 2019/9/1 21:41
     * @Author 61044
     */
    public void update(@Param("tableName") String tableName, @Param("id") Integer id, @Param("objects") Object[] objects);

    /**
     * @Description TODO 查单个
     * @Param [tableName 表名, id 序号]
     * @Return java.util.Map<java.lang.Object, java.lang.Object>
     * @Date 2019/9/3 8:53
     * @Author 61044
     */
    public Map<Object, Object> selectOne(@Param("tableName") String tableName, @Param("id") Integer id);

    /**
     * @Description TODO 查多个
     * @Param [tableName 表名]
     * @Return java.util.List<java.util.Map < java.lang.Object, java.lang.Object>>
     * @Date 2019/9/3 8:53
     * @Author 61044
     */
    List<Map<Object, Object>> selectAll(@Param("tableName") String tableName);

    void addForNotMatch(@Param("tableName") String tableName, @Param("fieldNames") Object[] fieldNames, @Param("fieldValues") Object[] fieldValues);
}