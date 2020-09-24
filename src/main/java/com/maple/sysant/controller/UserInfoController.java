package com.maple.sysant.controller;


import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserInfo;
import com.maple.sysant.entity.UserLogin;
import com.maple.sysant.mapper.UserInfoMapper;
import com.maple.sysant.service.UserInfoService;
import com.maple.sysant.service.UserLoginService;
import com.maple.sysant.shiro.AccountProfile;
import com.maple.sysant.vo.request.RegisterVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;


    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterVO vo){

        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(vo,user);
        user.setToken("令牌");
        user.setDeviceCode("devicecode");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setOffDate(LocalDateTime.now());

        //插入数据库成功
        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user,accountProfile);

        return Result.success(accountProfile);
    }
}
