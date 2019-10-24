package com.jones.dao;

import com.jones.model.Depart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DepartDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface DepartDao extends BaseDao{

    /**
     * description 删除部门和用户对应关系
     *
     * @param id
     * @return void
     */
    void delDeptUserRes(@Param("id") Integer id);

    /**
     * description 分页查找所有部门信息
     *
     * @param
     * @return java.util.List<com.jones.model.Depart>
     */
    List<Depart> selectAllByPage();
}
