package com.jones.service;

import com.github.pagehelper.PageInfo;
import com.jones.model.Area;

import java.util.List;

/**
 * AreaService
 *
 * @author JoNeS
 * @date
 */
public interface AreaService extends BaseService<Area>{



    void delAreaUserRes(Integer id);

    PageInfo<Area> selectAllByPage(Integer pageNum, Integer pageSize);

    /**
     * description 按照areasort排序查找所有区域
     *
     * @param
     * @return java.util.List<com.jones.model.Area>
     */
    List<Area> selectAllBysort();
}

