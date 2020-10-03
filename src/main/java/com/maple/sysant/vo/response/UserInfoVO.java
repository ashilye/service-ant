package com.maple.sysant.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfoVO implements Serializable {
    private String avatar;
    private String introduction;
    private String name;
    private List<String> roles;
    private String userId;

}
