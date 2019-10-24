package com.jones.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserDeptDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface UserDeptDao extends BaseDao{

    /**
     * description 通过用户id删除部门-用户表的信息
     *
     * @param id
     * @return void
     */
    void deleteByUid(@Param("id") Integer id);
}

