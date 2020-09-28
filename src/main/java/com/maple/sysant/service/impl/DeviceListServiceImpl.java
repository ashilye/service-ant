package com.maple.sysant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.maple.sysant.entity.DeviceList;
import com.maple.sysant.entity.UserInfo;
import com.maple.sysant.mapper.DeviceListMapper;
import com.maple.sysant.service.DeviceListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.sysant.service.UserInfoService;
import com.maple.sysant.service.UserLoginService;
import com.maple.sysant.vo.request.BindDeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
@Service
public class DeviceListServiceImpl extends ServiceImpl<DeviceListMapper, DeviceList> implements DeviceListService {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public int bindDevice(BindDeviceVO vo) {
        if(StringUtils.isEmpty(vo.getUserName()) || StringUtils.isEmpty(vo.getDeviceCode())){
            throw new RuntimeException("输入错误!!!");
        }

        UserInfo userInfo = userInfoService.getUserInfoByName(vo.getUserName());
        if(userInfo == null){
            throw new RuntimeException("绑定失败!");
        }

        if(!org.apache.commons.lang3.StringUtils.equals(vo.getDeviceCode(),userInfo.getDeviceCode())){
            throw new RuntimeException("设备码错误!");
        }

        if(StringUtils.isEmpty(vo.getDeviceModel()) || vo.getScreenWidth() <= 0 || vo.getScreenHeigth() <= 0){
            throw new RuntimeException("绑定失败!!!");
        }

        DeviceList deviceList = new DeviceList();
        deviceList.setUserId(userInfo.getId());
        deviceList.setDeviceStatus(0);
        deviceList.setDeviceModel(vo.getDeviceModel());
        deviceList.setScreenWidth(vo.getScreenWidth());
        deviceList.setScreenHeigth(vo.getScreenHeigth());
        return baseMapper.insert(deviceList);
    }

    @Override
    public List<DeviceList> getDeviceById(String id) {
        QueryWrapper<DeviceList> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        return baseMapper.selectList(wrapper);
    }
}
