package com.jones.dao;

import com.jones.model.Resource;
import com.jones.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface RoleDao extends BaseDao {
    /**
     * description 搜索所有Role的信息
     *
     * @param
     * @return java.util.List<com.jones.model.Role>
     */
    List<Role> selectAllRoles();

    /**
     * description 删除角色及资源关联
     *
     * @param id
     * @return void
     */
    void delRoleRes(@Param("id") Integer id);

    /**
     * description 删除角色及用户关联
     *
     * @param id
     * @return void
     */
    void delRoleUser(@Param("id") Integer id);

    /**
     * description 通过角色名字获取角色信息
     *
     * @param name
     * @return com.jones.model.Role
     */
    Role getRoleByName(@Param("name") String name);

    /**
     * description 通过id查询角色和对应资源信息
     *
     * @param id
     * @return com.jones.model.Role
     */
    Role selectRoleResById(@Param("id") Integer id);

    /**
     * description
     *
     * @param
     * @return com.jones.model.Resource
     */
    Resource selectRoleRes();

    /**
     * description
     *
     * @param id
     * @return void
     */
    void delRoleResource(@Param("id") Integer id);

    List<String> getRoleByuid(@Param("id") Integer id);
}

