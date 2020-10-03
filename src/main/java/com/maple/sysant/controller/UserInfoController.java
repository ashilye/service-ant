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
import com.maple.sysant.vo.response.UserInfoVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
@Slf4j
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


    @GetMapping("/getUser")
    public Result getUser(@RequestParam("token") String token){
        if(StringUtils.isEmpty(token)){
            return Result.fail("参数有误!");
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        String userName = claims.getSubject();
        log.info("getUser --userName :={}",userName);
        if(StringUtils.isEmpty(userName)){
            return Result.fail("userName 有误!");
        }

        UserInfo userInfo = userInfoService.getUserInfoByName(userName);
        if(userInfo == null){
            return Result.fail("用户不存在!");
        }


        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfoVO.setIntroduction(userInfo.getNickName());
        userInfoVO.setName(userInfo.getUserName());
        userInfoVO.setRoles(Arrays.asList(String.valueOf(userInfo.getUserType())));
        userInfoVO.setUserId(String.valueOf(userInfo.getId()));
        return Result.success("查询成功！",userInfoVO);
    }

}
