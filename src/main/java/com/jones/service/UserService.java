package com.jones.service;

import com.github.pagehelper.PageInfo;
import com.jones.model.Role;
import com.jones.model.Udiary;
import com.jones.model.User;
import com.jones.model.UserResult;

import java.util.List;

/**
 * UserService
 *
 * @author JoNeS
 * @date
 */
public interface UserService extends BaseService<User> {
    /**
     * description 添加用户
     *
     * @param user
     * @param roleIds
     * @param departmentId
     * @param areaId
     * @return void
     */
    void addUser(User user, Integer[] roleIds,Integer departmentId,Integer areaId);

    /**
     * description 用户关联查询
     *
     * @param
     * @return java.util.List<com.jones.model.User>
     */
    List<User> selectRelevanceUsers();

    /**
     * description  带分页的查询
     *
     * @param pageNum 第几页
     * @param pageSize 每页数据
     * @return com.github.pagehelper.PageInfo<com.jones.model.User>
     */
    public PageInfo<User> selectUsersByPager(int pageNum,int pageSize);

    /**
     * description 通过uid关联查询用户信息
     *
     * @param uid
     * @return com.jones.model.User
     */
    public User SelectRelUserByUid(Integer uid);

    /**
     * description 编辑用户
     *
     * @param user
     * @param roleIds
     * @param areaId
     * @param departmentId
     * @return void
     */
    void updateUser(User user, Integer[] roleIds, Integer areaId, Integer departmentId);

    /**
     * description 通过id删除用户的同时删除用户的关联角色
     *
     * @param uid
     * @return void
     */
    void deleteByUidRelRole(Integer uid);

    /**
     * description 通过id删除用户的同时删除用户的关联区域
     *
     * @param uid
     * @return void
     */
    void deleteByUidRelArea(Integer uid);

    /**
     * description 通过id删除用户的同时删除用户的关联部门
     *
     * @param uid
     * @return void
     */
    void deleteByUidRelDept(Integer uid);

    void batchDelUsersByIds(Integer[] uidArr);

    PageInfo<User> selectUsersBySearch(int pageNum, int pageSize, String userInfo);

    /**
     * description 验证登录
     *
     * @param username
     * @param password
     * @return void
     */
    User login(String username, String password);

    Role getLogRoleByUid(Integer id);

    List<UserResult> getUsersByRidpath(String idpath);

    /**
     * description  修改新密码
     *
     * @param trim 新密码
     * @param id  用户id
     * @return void
     */
    void updateUserPassword(String trim, Integer id);

    /**
     * description 通过名字查找该用户
     *
     * @param userInfo
     * @return com.jones.model.User
     */
    User selectUserByName(String userInfo);

    /**
     * description 通过uid和日期查询出具体的日志信息
     *
     * @param userInfo
     * @param dateInfo
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.jones.model.Udiary>
     */
    PageInfo<Udiary> selectDiaryByUidByDate(String userInfo, String dateInfo,Integer pageNum,Integer pageSize);


    void deldiaryByid(Integer id);

    User getUserByid(Integer id);



    //不需要写基本的增删改查
}


