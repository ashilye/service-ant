package com.maple.sysant.controller;


import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserLogin;
import com.maple.sysant.service.UserLoginService;
import com.maple.sysant.shiro.AccountProfile;
import com.maple.sysant.vo.request.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user-login")
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        UserLogin userLogin = userLoginService.getById(1);
        return Result.success(userLogin);
    }


    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginVO vo){
        log.info("login:={}",vo.getUserName()+"--"+vo.getPassword());
        return userLoginService.onLoginByUserName(vo);
    }
}
