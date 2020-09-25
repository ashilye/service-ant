package com.maple.sysant.controller;


import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserInfo;
import com.maple.sysant.entity.UserLogin;
import com.maple.sysant.mapper.UserInfoMapper;
import com.maple.sysant.service.UserInfoService;
import com.maple.sysant.service.UserLoginService;
import com.maple.sysant.shiro.AccountProfile;
import com.maple.sysant.shiro.JwtToken;
import com.maple.sysant.util.JwtUtils;
import com.maple.sysant.vo.request.RegisterVO;
import org.apache.commons.lang3.RandomStringUtils;
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

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterVO vo){
        return userInfoService.register(vo);
    }
}
