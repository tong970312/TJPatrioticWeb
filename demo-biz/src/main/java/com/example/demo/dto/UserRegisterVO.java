package com.example.demo.dto;

import lombok.Data;

@Data
public class UserRegisterVO {
    /**
     * 注册用户名
     */
    private String userName;
    /**
     * 注册密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * 验证邮箱
     */
    private String email;
    /**
     * 注册验证码
     */
    private String privateCode;
}
