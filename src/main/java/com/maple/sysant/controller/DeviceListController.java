package com.maple.sysant.controller;


import com.maple.sysant.common.lang.Result;
import com.maple.sysant.entity.DeviceList;
import com.maple.sysant.service.DeviceListService;
import com.maple.sysant.vo.request.BindDeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/device-list")
public class DeviceListController {

    @Autowired
    DeviceListService deviceListService;

    @PostMapping("/bind")
    public Result bindDevice(@Validated @RequestBody BindDeviceVO vo){
        int insert = deviceListService.bindDevice(vo);
        if(insert <= 0){
            return Result.fail("绑定失败!!!");
        }else {
            return Result.success("绑定成功!!!");
        }
    }

    @GetMapping("/getDeviceById")
    public Result getDeviceById(@RequestParam("userId") String userId){
        List<DeviceList> list = deviceListService.getDeviceById(userId);
        if(list != null && !list.isEmpty()){
            return Result.success(list);
        }else {
            return Result.fail("查询失败!!!");
        }
    }

}
