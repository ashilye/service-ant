package com.maple.sysant.vo.request;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RegisterVO implements Serializable {
    /**
     * 用户名 用来登陆  必须唯一
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户邮箱 用来找回密码
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
