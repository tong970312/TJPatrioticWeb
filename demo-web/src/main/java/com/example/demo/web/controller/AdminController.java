package com.example.demo.web.controller;

import com.example.demo.biz.AdminService;
import com.example.demo.dao.entity.UserInfo;
import com.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/login")
public class AdminController {
    @Autowired
    AdminService adminService;

//    @Autowired
//    RedisUtil redisUtil;
    @GetMapping(value = "/get")
    public UserInfo get(){
        UserInfo user  = adminService.print();
//        redisUtil.set(user.getUserName(),user.getPassword()+","+user.getEmail());
//        Object o  =redisUtil.get(user.getUserName());
//        System.out.println(o);
        return user;
    }

}
