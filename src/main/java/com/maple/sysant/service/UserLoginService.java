package com.maple.sysant.service;

import com.maple.sysant.entity.UserLogin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.sysant.shiro.AccountProfile;
import com.maple.sysant.vo.request.LoginVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
public interface UserLoginService extends IService<UserLogin> {
    int insertUser(UserLogin user);

    AccountProfile onLoginByUserName(LoginVO vo);
}
