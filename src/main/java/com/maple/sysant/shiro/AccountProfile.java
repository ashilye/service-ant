package com.maple.sysant.shiro;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountProfile implements Serializable {

    /**
     * 登陆令牌
     */
    private String token;

    /**
     * 用户名 用来登陆  必须唯一
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户邮箱 用来找回密码
     */
    private String email;

    /**
     * 设备码 用来绑定设备
     */
    private String deviceCode;

    /**
     * 账户状态
     */
    private Integer userStatus;

    /**
     * 账户级别
     */
    private Integer level;

    /**
     * 账户类型
     */
    private Integer userType;

    /**
     * 账户积分
     */
    private Integer integral;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
