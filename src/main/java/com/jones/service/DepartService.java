package com.jones.service;

import com.github.pagehelper.PageInfo;
import com.jones.model.Depart;

/**
 * DepartService
 *
 * @author JoNeS
 * @date
 */
public interface DepartService extends BaseService<Depart>{

    /**
     * description 删除部门和用户对应关系
     *
     * @param id
     * @return void
     */
    void delDeptUserRes(Integer id);

    /**
     * description 分页查询所有部门
     *
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.jones.model.Depart>
     */
    PageInfo<Depart> selectAllByPage(Integer pageNum, Integer pageSize);

}

