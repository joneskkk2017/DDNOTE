package com.jones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jones.dao.BaseDao;
import com.jones.dao.DepartDao;
import com.jones.model.Depart;
import com.jones.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DepartServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("departService")
public class DepartServiceImpl extends BaseServiceImpl<Depart> implements DepartService {

    @Autowired
    private DepartDao departDao;

    @Override
    public BaseDao getBaseDao() {
        return departDao;
    }

    /**
     * description 删除部门和用户对应关系
     *
     * @param id
     * @return void
     */
    @Override
    public void delDeptUserRes(Integer id) {
        departDao.delDeptUserRes(id);
    }

    /**
     * description 分页查找所有部门信息
     *
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.jones.model.Depart>
     */
    @Override
    public PageInfo<Depart> selectAllByPage(Integer pageNum, Integer pageSize) {

        Page<Depart> pager= PageHelper.startPage(pageNum,pageSize);
        List<Depart> deptDatas = departDao.selectAllByPage();
        PageInfo<Depart> info = new PageInfo<>(deptDatas);
        return info;
    }
}
