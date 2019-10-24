package com.jones.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
/**
 * User 用户表
 *
 * @author JoNeS
 * @date 2019/09/15
 */
public class User {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户手机号
     */
    private String telephone;

    /**
     * 用户所在部门
     */
    private Depart department;

    /**
     * 用户所在区域
     */
    private Area area;

    private List<Role> roles;

    /**
     * 激活状态
     */
    private Integer enable;

    /**
     * 添加日期
     */
    private Date addDate;
}

