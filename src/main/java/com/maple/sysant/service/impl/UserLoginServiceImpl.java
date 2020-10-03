package com.maple.sysant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.sysant.common.exception.GlobalException;
import com.maple.sysant.common.exception.GlobalExceptionHandler;
import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserInfo;
import com.maple.sysant.entity.UserLogin;
import com.maple.sysant.mapper.UserLoginMapper;
import com.maple.sysant.service.UserInfoService;
import com.maple.sysant.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.sysant.shiro.AccountProfile;
import com.maple.sysant.util.JwtUtils;
import com.maple.sysant.util.MD5Utils;
import com.maple.sysant.vo.request.LoginVO;
import com.maple.sysant.vo.response.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
@Slf4j
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {

    @Autowired
    UserLoginService userLoginService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public int insertUser(UserLogin user) {
        return baseMapper.insert(user);
    }



    @Override
    public Result onLoginByUserName(LoginVO vo) {
        if(StringUtils.isEmpty(vo.getUserName()) || StringUtils.isEmpty(vo.getPassword())){
            return Result.fail("用户名或密码错误!");
        }

        QueryWrapper<UserLogin> wrapper = new QueryWrapper();
        wrapper.eq("user_name",vo.getUserName());
        UserLogin userLogin = baseMapper.selectOne(wrapper);
        if(userLogin == null){
             return Result.fail("未注册!");
        }

        if(StringUtils.isEmpty(vo.getUserName()) || StringUtils.isEmpty(vo.getPassword())){
            return Result.fail("用户名或密码错误!!");
        }

        if(!StringUtils.equals(userLogin.getPassword(),MD5Utils.encrypt(vo.getPassword()))){
            return Result.fail("用户名或密码错误!!!");
        }

        UserInfo userInfo = userInfoService.getUserInfoByName(vo.getUserName());
        if(userInfo == null){
            return Result.fail("未注册!!!");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(userInfo,profile);
        return Result.success("查询成功！",profile);
    }

    @Override
    public Result login(LoginVO vo) {
        if(StringUtils.isEmpty(vo.getUserName()) || StringUtils.isEmpty(vo.getPassword())){
            return Result.fail("用户名或密码错误!");
        }
        QueryWrapper<UserLogin> wrapper = new QueryWrapper();
        wrapper.eq("user_name",vo.getUserName());
        UserLogin userLogin = baseMapper.selectOne(wrapper);
        if(userLogin == null){
            return Result.fail("未注册!");
        }

        if(StringUtils.isEmpty(vo.getUserName()) || StringUtils.isEmpty(vo.getPassword())){
            return Result.fail("用户名或密码错误!!");
        }

        if(!StringUtils.equals(userLogin.getPassword(),MD5Utils.encrypt(vo.getPassword()))){
            return Result.fail("用户名或密码错误!!!");
        }

        UserInfo userInfo = userInfoService.getUserInfoByName(vo.getUserName());
        if(userInfo == null){
            return Result.fail("未注册!!!");
        }
        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(userInfo.getToken());
        return Result.success("登录成功！",tokenVO);
    }

    @Override
    public Result logout(String token) {
        boolean isTokenExpired = jwtUtils.isTokenExpired(new Date());
        log.info("退出登录isTokenExpired:={}",isTokenExpired);
        //这里应该移除session 标记 或者 redis 中的登录标记等
        return Result.success("退出成功！",isTokenExpired);
    }
}
