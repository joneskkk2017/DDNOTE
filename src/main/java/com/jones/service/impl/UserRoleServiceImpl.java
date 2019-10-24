package com.jones.service.impl;

import com.jones.dao.BaseDao;
import com.jones.dao.UserRoleDao;
import com.jones.model.UserRole;
import com.jones.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserRoleServiceImpl
 *
 * @author JoNeS
 * @date
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public BaseDao getBaseDao() {
        return userRoleDao;
    }
}

