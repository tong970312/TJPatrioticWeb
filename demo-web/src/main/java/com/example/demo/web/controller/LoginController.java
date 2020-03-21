package com.example.demo.web.controller;

import com.common.ResultMessage;
import com.controller.BaseController;
import com.example.demo.biz.LoginService;
import com.example.demo.dao.entity.UserRegisterVO;
import com.example.demo.dto.LoginInfoReqDTO;
import com.util.MailUtil;
import com.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/admin/")
public class LoginController extends BaseController {
    @Autowired
    LoginService loginService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MailUtil mailUtil;

    /**
     * 用户注册
     * @param userRegisterVO
     * @return
     */
    @PostMapping(value = "/register")
    public ResultMessage register(@RequestBody UserRegisterVO userRegisterVO){
        return loginService.register(userRegisterVO);
    }

    /**
     * 用户登录
     * @param loginInfoReqDTO
     * @return
     */
    @PostMapping(value = "login")
    public ResultMessage login(@RequestBody LoginInfoReqDTO loginInfoReqDTO){
        return loginService.login(loginInfoReqDTO);
    }

    /**
     * 获取邮箱验证码
     * @param email
     * @return
     */
    @GetMapping("/sendValidCode")
    public ResultMessage getValidCodeBase64(@RequestParam(value = "email") String email) {
        return loginService.sendValidCode(email);
    }

    /**
     * 注册校验名字
     * @param userName
     * @return
     */
    @GetMapping("/checkAccount")
    public ResultMessage checkAccount(@RequestParam(value = "userName") String userName) {
        return loginService.checkAccount(userName);
    }


}
