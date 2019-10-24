package com.jones.shiro;

import com.jones.dao.RoleDao;
import com.jones.dao.UserDao;
import com.jones.model.Role;
import com.jones.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ShiroRealm
 *
 * @author JoNeS
 * @date
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * description 真正在做项目的时候,实现登录的验证工作
     *
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.强转
        UsernamePasswordToken upt = (UsernamePasswordToken) authenticationToken;
        //2.从upt中获取传过来的用户名
        String username = upt.getUsername();
        //3.通过username在数据库中查询有没有对应的用户信息记录
        User user = userDao.selectUserByUsername(username);
        //4.根据user的具体情况抛出shiro抛出的异常
        if(user == null){
            throw new UnknownAccountException("没有此用户");
        }
        AuthenticationInfo Info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return Info;
    }

    /**
     * description 专门用来做授权认证的方法
     *
     * @param principalCollection doGetAuthenticationInfo方法登录成功后传过来的用户信息
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.获取用户的登录信息.
        //注:doGetAuthenticationInfo的info中传入的User对象获取的就是User对象,输入的用户名就是用户名
        User user = principalCollection.oneByType(User.class);

        //2.获取用户的角色或者权限信息,没有包含的话就是数据库中查询 (new HashSet(list))
        List<String> roleList = roleDao.getRoleByuid(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> s = new HashSet<>(roleList);
        info.addRoles(s);
        return info;
    }
}
