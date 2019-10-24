package com.jones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jones.dao.BaseDao;
import com.jones.dao.RoleDao;
import com.jones.model.Role;
import com.jones.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public BaseDao getBaseDao() {
        return roleDao;
    }

    /**
     * description
     *
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.jones.model.Role>
     */
    @Override
    public PageInfo<Role> selectAllByPage(Integer pageNum, Integer pageSize) {
        Page<Role> pager= PageHelper.startPage(pageNum,pageSize);
        List<Role> roleDatas = roleDao.selectAllRoles();
        PageInfo<Role> info = new PageInfo<>(roleDatas);
        return info;
    }

    /**
     * description 删除角色及资源关联
     *
     * @param id
     * @return void
     */
    @Override
    public void delRoleRes(Integer id) {
        roleDao.delRoleRes(id);
    }

    /**
     * description 删除角色及用户关联
     *
     * @param id
     * @return void
     */
    @Override
    public void delRoleUser(Integer id) {
        roleDao.delRoleUser(id);
    }

    @Override
    public void addRole(Role role, Integer[] resourcesIds) {
        this.addForNotMatch(new Object[]{"name","code"},new Object[]{role.getName(),role.getCode()});
        Role role1 = roleDao.getRoleByName(role.getName());
        for(Integer resid:resourcesIds){
            roleDao.add("t_role_resource",new Object[]{null,role1.getId(),resid});
        }
    }

    /**
     * description 通过id查询角色和对应资源信息
     *
     * @param id
     * @return com.jones.model.Role
     */
    @Override
    public Role selectRoleResById(Integer id) {

        return roleDao.selectRoleResById(id);
    }

    /**
     * description 更新角色同时更新和资源的关系
     *
     * @param role
     * @param resourceIds
     * @return void
     */
    @Override
    public void updateRoleRes(Role role, Integer[] resourceIds) {

        if(resourceIds != null) {
            this.update(role);
            roleDao.delRoleResource(role.getId());
            for (Integer id : resourceIds) {
                roleDao.add("t_role_resource", new Object[]{null, role.getId(), id});
            }
        }else{
            this.update(role);
        }

    }

    @Override
    public void addRole1(Role role) {
        roleDao.add("t_role",new Object[]{null,role.getName(),role.getCode(),role.getIdpath()});
    }
}
