package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginInfoReqDTO {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

}
