package com.jones.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserArea
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface UserAreaDao extends BaseDao{
    void deleteByUid(@Param("id") Integer id);
}


