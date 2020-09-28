package com.maple.sysant.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class BindDeviceVO implements Serializable {
    /**
     * 用户名 用来登陆  必须唯一
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 设备号
     */
    @NotBlank(message = "设备号不能为空")
    private String deviceCode;

    /**
     * 设备状态
     */
    private Integer deviceStatus;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 屏幕宽度
     */
    private Integer screenWidth;

    /**
     * 屏幕高度
     */
    private Integer screenHeigth;
}
