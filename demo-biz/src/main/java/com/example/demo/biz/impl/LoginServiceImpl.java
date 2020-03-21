package com.example.demo.biz.impl;

import com.common.ResultMessage;
import com.enums.SysConstant;
import com.example.demo.biz.LoginService;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dao.entity.UserRegisterVO;
import com.example.demo.dao.repository.UserInfoRepository;
import com.example.demo.dto.LoginInfoReqDTO;
import com.example.demo.dto.LoginInfoResDTO;
import com.exception.ServiceException;
import com.util.BeanMapperUtils;
import com.util.JsonUtil;
import com.util.MailUtil;
import com.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MailUtil mailUtil;
    protected static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private static final char[] VALID_CODE_CHARS = {'1', '2', '3', '4', '5', '6', '7', '8', '9','0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    /**
     * 登录
     * @param loginInfoReqDTO
     * @return
     */
    @Override
    public ResultMessage login(LoginInfoReqDTO loginInfoReqDTO) {
        if (loginInfoReqDTO == null) {
             logger.error("参数不能为空");
             return new ResultMessage(SysConstant.FAIL_CODE,"参数不能为空");
        }
        if (StringUtils.isBlank(loginInfoReqDTO.getUserName())) {
            logger.error("用户名不能为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"用户名不能为空");
        }
        if (StringUtils.isBlank(loginInfoReqDTO.getPassword())) {
            logger.error("密码不能为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"密码不能为空");
        }

        UserInfo userInfo = checkUser(loginInfoReqDTO.getUserName(),loginInfoReqDTO.getPassword());
        if (userInfo == null) {
            return new ResultMessage(SysConstant.FAIL_CODE,"登录失败,请核对信息");
        }
        LoginInfoResDTO loginInfoResDTO;
        try {
             loginInfoResDTO  = BeanMapperUtils.map(userInfo,LoginInfoResDTO.class);
        } catch (Exception e) {
            logger.error("结果换砖异常");
            return new ResultMessage(SysConstant.FAIL_CODE,"返回结果错误");
        }
        //将登陆用户的信息存入redis
        redisUtil.set(loginInfoResDTO.getUserNo(), JsonUtil.ObjToStr(loginInfoResDTO));
        return new ResultMessage(SysConstant.SUCCESS_CODE,"登陆成功",loginInfoResDTO);
    }

    /**
     * 注册
     * @param userRegisterVO
     * @return
     */
    @Override
    public ResultMessage register(UserRegisterVO userRegisterVO) {
        if (userRegisterVO == null) {
            logger.error("参数不能为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"参数不能为空");
        }
        if (StringUtils.isBlank(userRegisterVO.getUserName())) {
            logger.error("用户名不能为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"用户名不能为空");
        }
        if (StringUtils.isBlank(userRegisterVO.getPassword())) {
            logger.error("密码不能为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"密码不能为空");
        }
        if (StringUtils.isBlank(userRegisterVO.getEmail())) {
            logger.error("验证邮箱不能为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"验证邮箱不能为空");
        }
        if (StringUtils.isBlank(userRegisterVO.getPrivateCode())) {
            logger.error("验证码不能为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"验证码不能为空");
        }
        //获取redis里的验证码值
        String result = String.valueOf(redisUtil.get(userRegisterVO.getEmail()));
        //取值为null，验证码过期
        if (StringUtils.equals("null",result)) {
            return new ResultMessage(SysConstant.FAIL_CODE,"验证码已失效");
        }
        //对比验证码是否相等
        if (!StringUtils.equals(userRegisterVO.getPrivateCode().toLowerCase(),result)){
            return new ResultMessage(SysConstant.FAIL_CODE,"验证码错误");
        }
        UserInfo userInfo = BeanMapperUtils.map(userRegisterVO,UserInfo.class);
        userInfo.setUserNo(generateTeamCode());
        userInfo.setUserType(1);
        userInfo.setDelFlag(0);
        userInfo.setCreateDate(new Date());
        userInfo.setCreateUid("用户注册");
        Integer insert = userInfoRepository.insertSelective(userInfo);
        if (insert <= 0) {
            return new ResultMessage(SysConstant.FAIL_CODE,"注册失败");
        }
        return new ResultMessage(SysConstant.SUCCESS_CODE,"注册成功");
    }

    /**
     * 登录校验用户是否存在
     * @param userName
     * @param password
     * @return
     */
    private UserInfo checkUser(String userName, String password) {
        return  userInfoRepository.checkUser(userName,password);
    }



    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    @Override
    public ResultMessage sendValidCode(String email) {
        if (StringUtils.isBlank(email)) {
            return new ResultMessage(SysConstant.FAIL_CODE,"请输入正确的邮箱地址");
        }
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        //生成验证码
        for (int i = 0; i < 6; i++) {
            String str = String.valueOf(VALID_CODE_CHARS[random.nextInt(VALID_CODE_CHARS.length)]);
            builder.append(str);
        }
        String validCode = builder.toString().toLowerCase();
        logger.info("目标邮箱"+email);
        logger.info("验证码"+validCode);
        //存入redis
        try {
            redisUtil.set(email,validCode,600);
        } catch (Exception e) {
            throw new  ServiceException("验证码存入redis失败");
        }
        //发送邮箱
        try {
            mailUtil.sendRegisterCodeEmail(email,validCode);
        } catch (Exception e) {
            logger.error("发送邮箱验证码失败");
            throw new  ServiceException("发送邮箱验证码失败");
        }
        return new ResultMessage(SysConstant.SUCCESS_CODE,"发送邮箱验证码成功") ;
    }

    /**
     * 登录校验参数
     * @param userName
     * @return
     */
    @Override
    public ResultMessage checkAccount(String userName) {
        if (StringUtils.isBlank(userName)) {
            logger.error("登录校验参数错误");
            return new ResultMessage(SysConstant.FAIL_CODE,"校验用户名错误");
        }
        String check = userInfoRepository.checkName(userName);
        if (!StringUtils.isBlank(check)) {
            return new ResultMessage(SysConstant.FAIL_CODE,"当前用户名已存在");
        }
        return new ResultMessage(SysConstant.SUCCESS_CODE,"当前用户名可用");
    }

    /**
     * JYJD+YYMM+四位自增
     * 用户编号规则
     * @return
     * @author litong
     * date 2020-03-19
     */
    private String generateTeamCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String userNoCode = "JYJD"+dateStr.substring(2,6);
        //查询当月生成的最后一个编号
        String teamCode  = userInfoRepository.getUserNoCode(userNoCode);
        //当月最后一个编码存在
        if (StringUtils.isNotEmpty(teamCode)){
            //当前编码为最高999
            String num = teamCode.substring(9,teamCode.length());
            if (StringUtils.equals("99999",num)){
                throw new ServiceException("创建用户编号失败");
            }
            //当前编码+1
            userNoCode = userNoCode + String.format("%05d", Integer.valueOf(num)+ 1);
        }else{
            //如果不存在，那么当月生成的编码为空，设置为当月第一个
            userNoCode = userNoCode+"00001";
        }
        return userNoCode;
    }

}
