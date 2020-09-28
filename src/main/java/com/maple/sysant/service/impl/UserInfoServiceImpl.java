package com.maple.sysant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.sysant.common.exception.GlobalExceptionHandler;
import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserInfo;
import com.maple.sysant.entity.UserLogin;
import com.maple.sysant.mapper.UserInfoMapper;
import com.maple.sysant.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.sysant.service.UserLoginService;
import com.maple.sysant.shiro.AccountProfile;
import com.maple.sysant.util.MD5Utils;
import com.maple.sysant.vo.request.RegisterVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    UserLoginService userLoginService;

    @Override
    public Result register(RegisterVO vo) {

        Map<String, Object> map = new HashMap<>();
        map.put("user_name",vo.getUserName());
        List<UserInfo> userInfos = baseMapper.selectByMap(map);
        if(userInfos == null || userInfos.isEmpty()){
            UserInfo user = new UserInfo();
            BeanUtils.copyProperties(vo,user);
            user.setToken("token");
            user.setDeviceCode(RandomStringUtils.randomNumeric(6));
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            user.setOffDate(LocalDateTime.now());

            UserLogin login = new UserLogin();
            login.setUserName(vo.getUserName());
            login.setPassword(MD5Utils.encrypt(vo.getPassword()));
            int loginInsert = userLoginService.insertUser(login);
            //插入数据库成功
            int userInsert = baseMapper.insert(user);
            if(userInsert <= 0 || loginInsert <= 0){
                //添加失败
                return new GlobalExceptionHandler().handler(new RuntimeException("注册失败!!!"));
            }
            AccountProfile accountProfile = new AccountProfile();
            BeanUtils.copyProperties(user,accountProfile);
            return Result.success(null);
        }else {
            return Result.fail(409,"账号已存在！！！");
        }
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper();
        wrapper.eq("user_name",userName);
        return baseMapper.selectOne(wrapper);
    }
}
