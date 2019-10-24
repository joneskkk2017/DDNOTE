package com.jones.service.impl;

import com.jones.dao.BaseDao;
import com.jones.dao.UserDeptDao;
import com.jones.model.UserDept;
import com.jones.service.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserDeptServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("userDeptService")
public class UserDeptServiceImpl extends BaseServiceImpl<UserDept> implements UserDeptService {

    @Autowired
    UserDeptDao userDeptDao;

    @Override
    public BaseDao getBaseDao() {
        return userDeptDao;
    }
}

