package com.maple.sysant.vo.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginVO implements Serializable {
    /**
     * 用户名 用来登陆  必须唯一
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 用户手机号
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
