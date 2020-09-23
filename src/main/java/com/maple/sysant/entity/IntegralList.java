package com.maple.sysant.entity;

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
public class IntegralList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 设备id
     */
    private Integer deviceId;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 积分总数
     */
    private Integer total;

    /**
     * 积分单数
     */
    private Integer number;


}
