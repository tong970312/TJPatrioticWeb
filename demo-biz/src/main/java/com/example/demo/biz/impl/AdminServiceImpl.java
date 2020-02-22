package com.example.demo.biz.impl;

import com.example.demo.biz.AdminService;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dao.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl  implements AdminService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserInfo print() {
        return userInfoRepository.selectByPrimaryKey("1");
    }
}
