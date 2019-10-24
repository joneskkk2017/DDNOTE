package com.jones.service.impl;

import com.jones.dao.BaseDao;
import com.jones.dao.UserAreaDao;
import com.jones.model.UserArea;
import com.jones.service.UserAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserAreaServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("userAreaService")
public class UserAreaServiceImpl extends BaseServiceImpl<UserArea> implements UserAreaService {

    @Autowired
    UserAreaDao userAreaDao;

    @Override
    public BaseDao getBaseDao() {
        return userAreaDao;
    }
}

