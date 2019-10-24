package com.jones.dao;

import com.jones.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao
 *
 * @author JoNeS
 * @date
 */
@Repository
public interface UserDao extends BaseDao {
    //不用写基本的增删改查,只需要写特殊的

    /**
     * description 根据用户名称获取用户对象
     *
     * @param username
     * @return com.jones.model.User
     */
    User getUserByName(@Param("username") String username);

    /**
     * description 用户关联查询
     *
     * @param
     * @return void
     */
    List<User> selectRelevanceUsers();

    Role getRoleByUid();

    Depart getDeptByUid();

    Area getAreaByUid();

    /**
     * description 通过User的id查询信息,关联其他信息
     *
     * @param id
     * @return com.jones.model.User
     */
    public User getUserByUid(@Param("id") Integer id);

    /**
     * description 通过名字查询用户信息
     *
     * @param userInfo
     * @return java.util.List<com.jones.model.User>
     */
    List<User> selectUsersBySearch(@Param("userInfo") String userInfo);

    /**
     * description shiro通过用户名查询用户信息
     *
     * @param username
     * @return void
     */
    User selectUserByUsername(@Param("username") String username);

    /**
     * description 登录后通过登录用户的id查询用户关联的角色
     *
     * @param id
     * @return void
     */
    Role getLogRoleByUid(@Param("id") Integer id);

    /**
     * description 通过角色idpath获取用户信息
     *
     * @param idpath
     * @return java.util.List<com.jones.model.User>
     */
    List<UserResult> getUsersByRidpath(@Param("idpath") String idpath);

    /**
     * description 修改
     *
     * @param trim
     * @param id
     * @return void
     */
    void updateUserPassword(@Param("trim") String trim, @Param("id") Integer id);


    List<Udiary> selectDiaryByUidByDate(@Param("userInfo") String userInfo,@Param("dateInfo") String dateInfo);
}

