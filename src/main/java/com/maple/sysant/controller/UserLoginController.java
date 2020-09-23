package com.maple.sysant.controller;


import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserLogin;
import com.maple.sysant.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/user-login")
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;

    @GetMapping("/index")
    public Result index(){
        UserLogin userLogin = userLoginService.getById(1);
        return Result.success(userLogin);
    }
}
