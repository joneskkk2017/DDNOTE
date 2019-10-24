package com.jones.service;

import com.github.pagehelper.PageInfo;
import com.jones.model.Resource;

import java.util.List;

/**
 * ResourceService
 *
 * @author JoNeS
 * @date
 */
public interface ResourceService extends BaseService<Resource> {
    void initPathes(List<String> resources);

    PageInfo<Resource> selectAllByPage(Integer pageNum, Integer pageSize);

    void delResourceRes(Integer id);

    List<Resource> selectRoleResById(Integer id);

}

