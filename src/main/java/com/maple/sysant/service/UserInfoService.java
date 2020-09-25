package com.maple.sysant.service;

import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.sysant.vo.request.RegisterVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
public interface UserInfoService extends IService<UserInfo> {

    Result register(RegisterVO vo);
}
