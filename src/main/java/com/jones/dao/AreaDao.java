package com.jones.dao;

import com.jones.model.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AreaDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface AreaDao extends BaseDao {
    /**
     * description 删除区域与用户的关系
     *
     * @param id
     * @return void
     */
    void delAreaUserRes(@Param("id") Integer id);


    /**
     * description 分页查询区域
     *
     * @param
     * @return java.util.List<com.jones.model.Area>
     */
    List<Area> selectAllByPage();

    /**
     * description 按照areasort排序查找所有区域
     *
     * @param
     * @return java.util.List<com.jones.model.Area>
     */
    List<Area> selectAllBysort();
}

