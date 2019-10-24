package com.jones.dao;

import com.jones.model.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResourceDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface ResourceDao extends BaseDao{
    /**
     * description 通过路径查询资源
     *
     * @param path
     * @return int
     */
    int selectCountResByPath(@Param("path") String path);

    /**
     * description 查询所有资源信息
     *
     * @param
     * @return java.util.List<com.jones.model.Resource>
     */
    List<Resource> selectAllReses();

    /**
     * description 删除资源与角色的绑定
     *
     * @param id
     * @return void
     */
    void delResourceRes(@Param("id") Integer id);

    List<Resource> selectRoleResById(@Param("rid")Integer rid);

}


