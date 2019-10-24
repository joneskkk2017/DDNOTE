package com.jones.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserRoleDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface UserRoleDao extends BaseDao{

    /**
     * description 通过用户id删除角色-用户表的信息
     *
     * @param uid
     * @return void
     */
    void deleteByUid(@Param("uid") Integer uid);
}

