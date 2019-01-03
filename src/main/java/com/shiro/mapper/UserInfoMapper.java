package com.shiro.mapper;

import com.shiro.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @作者: 夏容清
 * @创建日期: 2018/12/27 19:22
 * @说明: 用户dao层
 */

public interface UserInfoMapper {

    //用户登录
    UserInfo findByUsername(@Param("username") String username);

}
