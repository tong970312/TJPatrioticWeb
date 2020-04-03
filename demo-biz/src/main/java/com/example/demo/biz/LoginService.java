package com.example.demo.biz;

import com.common.ResultMessage;
import com.example.demo.dto.UserRegisterVO;
import com.example.demo.dto.LoginInfoReqDTO;

public interface LoginService {

    /**
     * 用户登录
     * @param loginInfoReqDTO
     * @return
     */
    ResultMessage login(LoginInfoReqDTO loginInfoReqDTO);

    /**
     * 用户注册
     * @param userRegisterVO
     * @return
     */
    ResultMessage register(UserRegisterVO userRegisterVO);

    /**
     * 获取邮箱验证码
     * @param email
     * @return
     */
    ResultMessage sendValidCode(String email);

    /**
     * 注册校验名字
     * @param userName
     * @return
     */
    ResultMessage checkAccount(String userName);
}
