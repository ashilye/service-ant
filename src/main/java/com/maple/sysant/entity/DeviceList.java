package com.maple.sysant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author gaoguanqi
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

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
