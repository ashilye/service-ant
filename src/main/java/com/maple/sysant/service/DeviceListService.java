package com.maple.sysant.service;

import com.maple.sysant.entity.DeviceList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.sysant.vo.request.BindDeviceVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
public interface DeviceListService extends IService<DeviceList> {

    int bindDevice(BindDeviceVO vo);

    List<DeviceList> getDeviceById(String id);
}
