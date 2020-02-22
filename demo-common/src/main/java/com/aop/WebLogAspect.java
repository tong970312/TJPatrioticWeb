package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义切面类，实现web层的日志切面
 *  aspect 能获取处理请求的controller,method,请求参数，
 *  但是不能获取原始的request,response
 *  执行顺序：
 *  filter->interceptor->aspect->controller
 *  如果controller抛出异常
 *  最先捕获的是aspect、（如果存在）@controllerAdvice、interceptor(afterHandle)
 *  最后是filter
 *
 */
@Component //交给Spring容器管理
@Aspect //标注其为切面类
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 定义切入点。
     * 位置是com.example.demo包下所有函数
     */
    @Pointcut("execution(public * com.example.demo.web.controller.*.*(..))")
    public void aspectLog(){}

    /**
     * 在调用方法前执行
     * @param joinPoint
     * @throws Throwable
     */
    @Before("aspectLog()")
    public void doBefore(JoinPoint joinPoint) throws  Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        //当前请求类型
        //logger.info("http_method : " + request.getMethod());
        //当前ip
        //logger.info("IP : " + request.getRemoteAddr());
        //输出到当前Controller
        //logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + ".");
        //输出当前调用的方法
        //logger.info("CLASS_METHOD : " + joinPoint.getSignature().getName());
        //完整路径
//        logger.info("CLASS_METHOD -- BEGIN : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("CLASS_METHOD --  BEGIN :  " + joinPoint.getSignature().getDeclaringTypeName().substring(joinPoint.getSignature().getDeclaringTypeName().indexOf("controller")) + "." + joinPoint.getSignature().getName());

        //返回结果参数
        //logger.info("PARAMS : " + Arrays.toString(joinPoint.getArgs()));
//        String allName = joinPoint.getSignature().getDeclaringTypeName();
//        String controllerName = allName.substring(joinPoint.getSignature().getDeclaringTypeName().lastIndexOf(".")+1);
//        logger.info("调用 "+controllerName+" ："+joinPoint.getSignature().getName());
    }

    /**
     * 调用完方法后执行
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret",pointcut = "aspectLog()")
    public void doAfterReturning(JoinPoint joinPoint,Object ret) throws Throwable{

//        logger.info("CLASS_METHOD --  END :  " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("CLASS_METHOD --  END :  " + joinPoint.getSignature().getDeclaringTypeName().substring(joinPoint.getSignature().getDeclaringTypeName().indexOf("controller")) + "." + joinPoint.getSignature().getName());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getName() +"调用结束");
        // 处理完请求，返回内容
      //  logger.info("RESPONSE : " + ret);
    }
}
