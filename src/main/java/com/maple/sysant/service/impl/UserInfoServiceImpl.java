package com.maple.sysant.service.impl;

import com.maple.sysant.common.exception.GlobalExceptionHandler;
import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserInfo;
import com.maple.sysant.mapper.UserInfoMapper;
import com.maple.sysant.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.sysant.shiro.AccountProfile;
import com.maple.sysant.vo.request.RegisterVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public Result register(RegisterVO vo) {

        UserInfo user = new UserInfo();
        BeanUtils.copyProperties(vo,user);
        user.setToken("token");
        user.setDeviceCode(RandomStringUtils.randomNumeric(6));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setOffDate(LocalDateTime.now());

        //插入数据库成功
        int insert = baseMapper.insert(user);
        if(insert <= 0){
            //添加失败
            return new GlobalExceptionHandler().handler(new RuntimeException("注册失败!!!"));
        }
        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user,accountProfile);
        return Result.success(null);
    }
}
