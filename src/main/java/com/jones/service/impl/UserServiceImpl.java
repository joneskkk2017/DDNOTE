package com.jones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jones.dao.*;
import com.jones.model.Role;
import com.jones.model.Udiary;
import com.jones.model.User;
import com.jones.model.UserResult;
import com.jones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * UserServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDeptDao userDeptDao;

    @Autowired
    private UserAreaDao userAreaDao;

    @Override
    public BaseDao getBaseDao() {

        return userDao;
    }

    /**
     * description 添加用户,并保存用户与角色的关系
     *
     * @param user
     * @param roleIds
     * @return void
     */
    @Override
    public void addUser(User user, Integer[] roleIds,Integer departmentId,Integer areaId) {
        //时间转化
        Date date = new Date();
        long l = date.getTime();


        this.addForNotMatch(new Object[]{"username","password","telephone","enable","add_date"},
                new Object[]{user.getUsername(),user.getPassword(),user.getTelephone(),1,new java.sql.Date(l)});
        //2.保存用户与角色的关系到关系表
        User u = userDao.getUserByName(user.getUsername());
        if(roleIds != null){
            for(Integer rid:roleIds){
                userRoleDao.add("t_user_role",new Object[]{null,u.getId(),rid});
            }
            userDeptDao.add("t_user_dep",new Object[]{null,u.getId(),departmentId});
            userAreaDao.add("t_user_area",new Object[]{null,u.getId(),areaId});
        }else{
            userDeptDao.add("t_user_dep",new Object[]{null,u.getId(),departmentId});
            userAreaDao.add("t_user_area",new Object[]{null,u.getId(),areaId});
        }
    }

    /**
     * description 用户关联查询
     *
     * @param
     * @return java.util.List<com.jones.model.User>
     */
    @Override
    public List<User> selectRelevanceUsers() {
        return userDao.selectRelevanceUsers();
    }

    /**
     * description  带分页的查询
     *
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.jones.model.User>
     */

    @Override
    public PageInfo<User> selectUsersByPager(int pageNum, int pageSize) {
        Page<User> pager= PageHelper.startPage(pageNum,pageSize);
        List<User> userDatas = userDao.selectRelevanceUsers();
        PageInfo<User> info = new PageInfo<>(userDatas);
        return info;
    }

    /**
     * description 通过uid关联查询用户信息
     *
     * @param uid
     * @return com.jones.model.User
     */
    @Override
    public User SelectRelUserByUid(Integer uid) {
        return userDao.getUserByUid(uid);
    }

    /**
     * description 编辑用户
     *
     * @param user
     * @param roleIds
     * @param areaId
     * @param departmentId
     * @return void
     */
    @Override
    public void updateUser(User user, Integer[] roleIds, Integer areaId, Integer departmentId) {
        if("".equals(user.getPassword().trim())){
            user.setPassword(null);
        }
        Integer uid = user.getId();
        //修改用户的第一步
        this.update(user);
        //修改用户的第二步:修改用户关联的信息
        //①删除用户id时user.getId()的所有关联的信息
        userRoleDao.deleteByUid(user.getId());
        userAreaDao.deleteByUid(user.getId());
        userDeptDao.deleteByUid(user.getId());

        if(roleIds != null) {
            //②把接收到的新的信息添加到表中
            for (Integer rid : roleIds) {
                userRoleDao.add("t_user_role", new Object[]{null, user.getId(), rid});
            }
            userDeptDao.add("t_user_dep", new Object[]{null, user.getId(), departmentId});
            userAreaDao.add("t_user_area", new Object[]{null, user.getId(), areaId});

        }else{

            userDeptDao.add("t_user_dep", new Object[]{null, user.getId(), departmentId});
            userAreaDao.add("t_user_area", new Object[]{null, user.getId(), areaId});
        }
    }

    /**
     * description 通过id删除用户的同时删除用户的关联角色
     *
     * @param uid
     * @return void
     */
    @Override
    public void deleteByUidRelRole(Integer uid) {
        userRoleDao.deleteByUid(uid);
    }

    /**
     * description 通过id删除用户的同时删除用户的关联区域
     *
     * @param uid
     * @return void
     */
    @Override
    public void deleteByUidRelArea(Integer uid) {
        userAreaDao.deleteByUid(uid);
    }

    /**
     * description 通过id删除用户的同时删除用户的关联部门
     *
     * @param uid
     * @return void
     */
    @Override
    public void deleteByUidRelDept(Integer uid) {
        userDeptDao.deleteByUid(uid);
    }

    @Override
    public void batchDelUsersByIds(Integer[] uidArr) {
        for(Integer id:uidArr){
            this.deleteByUidRelArea(id);
            this.deleteByUidRelDept(id);
            this.deleteByUidRelRole(id);
            this.delete(id);
        }
    }

    @Override
    public PageInfo<User> selectUsersBySearch(int pageNum, int pageSize, String userInfo) {

        Page<User> pager= PageHelper.startPage(pageNum,pageSize);
        List<User> userDatas = userDao.selectUsersBySearch("%"+userInfo+"%");
        PageInfo<User> info = new PageInfo<>(userDatas);
        return info;
    }

    /**
     * description 验证登录
     *
     * @param username
     * @param password
     * @return void
     */
    @Override
    public User login(String username, String password) {
        User user = userDao.selectUserByUsername(username);
        if(user == null){
            throw new RuntimeException("用户名或者密码有误");
        }
        return null;
    }

    /**
     * description 登录后通过登录用户的id查询用户关联的角色
     *
     * @param id
     * @return void
     */
    @Override
    public Role getLogRoleByUid(Integer id) {

        return userDao.getLogRoleByUid(id);
    }

    @Override
    public List<UserResult> getUsersByRidpath(String idpath) {
        return userDao.getUsersByRidpath(idpath);
    }

    /**
     * description 修改密码
     *
     * @param trim
     * @param id
     * @return void
     */
    @Override
    public void updateUserPassword(String trim, Integer id) {
        userDao.updateUserPassword(trim,id);
    }

    @Override
    public User selectUserByName(String userInfo) {

        return userDao.getUserByName(userInfo);
    }

    @Override
    public PageInfo<Udiary> selectDiaryByUidByDate(String userInfo, String dateInfo,Integer pageNum,Integer pageSize) {

        Page<Udiary> pager= PageHelper.startPage(pageNum,pageSize);
        List<Udiary> userDatas = userDao.selectDiaryByUidByDate(userInfo,dateInfo);
        PageInfo<Udiary> info = new PageInfo<>(userDatas);
        return info;
    }

    @Override
    public void deldiaryByid(Integer id) {
        userDao.delete("t_diary",id);
    }

    @Override
    public User getUserByid(Integer id) {

        return userDao.getUserByUid(id);
    }


}


























