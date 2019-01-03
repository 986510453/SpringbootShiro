package com.shiro.service;

import com.shiro.bean.UserInfo;
import com.shiro.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @作者: 夏容清
 * @创建日期: 2018/12/27 19:03
 * @说明: 用户逻辑层
 */
@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /*/**
     * @行参说明: 用户名
     * @返回值说明: 用户对象
     * @作者:  夏容清
     * @创建日期: 2018/12/27 19:26
     * @功能描述: 用户登录
     */
    public UserInfo findByUsername(String username){
        UserInfo user = userInfoMapper.findByUsername(username);
        return user;
    }


}
