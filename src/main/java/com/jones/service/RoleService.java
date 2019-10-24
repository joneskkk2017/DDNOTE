package com.jones.service;

import com.github.pagehelper.PageInfo;
import com.jones.model.Role;

/**
 * RoleService
 *
 * @author JoNeS
 * @date
 */
public interface RoleService extends BaseService<Role>{
    PageInfo<Role> selectAllByPage(Integer pageNum, Integer pageSize);

    void delRoleRes(Integer id);

    void delRoleUser(Integer id);

    void addRole(Role role, Integer[] resourcesIds);

    /**
     * description 通过id查询角色和对应资源信息
     *
     * @param id
     * @return com.jones.model.Role
     */
    Role selectRoleResById(Integer id);

    /**
     * description 更新角色同时更新和资源的关系
     *
     * @param role
     * @param resourceIds
     * @return void
     */
    void updateRoleRes(Role role, Integer[] resourceIds);

    /**
     * description 当资源为空的时候添加角色
     *
     * @param role
     * @return void
     */
    void addRole1(Role role);
}



