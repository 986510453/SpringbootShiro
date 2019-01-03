package com.shiro.config;

import com.shiro.bean.SysPermission;
import com.shiro.bean.SysRole;
import com.shiro.bean.UserInfo;
import com.shiro.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @作者: 夏容清
 * @创建日期: 2018/12/27 18:56
 * @说明: 登录拦截,权限配置
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private HttpSession session;

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        if(userInfo == null){
            return null;
        }
        session.setAttribute("users",userInfo);
        ByteSource newSalt = ByteSource.Util.bytes(userInfo.getSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token.getPrincipal(),
                userInfo.getPassword(), newSalt, getName());
        return authenticationInfo;
    }

    //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录用户名
        //String name= (String) principals.getPrimaryPrincipal();
        //查询用户名称
        UserInfo user = (UserInfo) session.getAttribute("users");
        if(user == null){
            user = userInfoService.findByUsername((String) principals.getPrimaryPrincipal());
            session.setAttribute("users",user);
        }
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (SysRole role:user.getRoleList()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRole());
            for (SysPermission permission:role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

}
