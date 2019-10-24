package com.jones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jones.dao.BaseDao;
import com.jones.dao.ResourceDao;
import com.jones.model.Resource;
import com.jones.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ResourceServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;
    @Override
    public BaseDao getBaseDao() {
        return resourceDao;
    }

    /**
     * description 保存资源路径
     *
     * @param resources
     * @return void
     */
    @Override
    public void initPathes(List<String> resources) {
        //1.把数据插入到数据库
        int resCount = 0;
        for(String path:resources){
            resCount = resourceDao.selectCountResByPath(path);
            if(resCount == 0){
                this.add(new Resource(path));
            }
        }
    }

    @Override
    public PageInfo<Resource> selectAllByPage(Integer pageNum, Integer pageSize) {
        Page<Resource> pager= PageHelper.startPage(pageNum,pageSize);
        List<Resource> resourceDatas = resourceDao.selectAllReses();
        PageInfo<Resource> info = new PageInfo<>(resourceDatas);
        return info;
    }

    @Override
    public void delResourceRes(Integer id) {
        resourceDao.delResourceRes(id);
    }

    @Override
    public List<Resource> selectRoleResById(Integer id) {
        List<Resource> resourceList = resourceDao.selectRoleResById(id);
        return resourceList;
    }
}

