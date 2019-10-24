package com.jones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jones.dao.AreaDao;
import com.jones.dao.BaseDao;
import com.jones.model.Area;
import com.jones.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AreaServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {


    @Autowired
    private AreaDao areaDao;

    @Override
    public BaseDao getBaseDao() {
        return areaDao;
    }

    @Override
    public void delAreaUserRes(Integer id) {
        areaDao.delAreaUserRes(id);
    }

    @Override
    public PageInfo<Area> selectAllByPage(Integer pageNum, Integer pageSize) {

        Page<Area> pager= PageHelper.startPage(pageNum,pageSize);
        List<Area> areaDatas = areaDao.selectAllByPage();
        PageInfo<Area> info = new PageInfo<>(areaDatas);
        return info;
    }

    /**
     * description 按照areasort排序查找所有区域
     *
     * @param
     * @return java.util.List<com.jones.model.Area>
     */
    @Override
    public List<Area> selectAllBysort() {
        return areaDao.selectAllBysort();
    }
}

