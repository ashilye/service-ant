package com.maple.sysant.entity;

import java.time.LocalDateTime;
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
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 充值的额度
     */
    private Integer limit;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单类型
     */
    private Long orderType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
