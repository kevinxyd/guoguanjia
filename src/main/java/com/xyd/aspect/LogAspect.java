package com.xyd.aspect;

import com.xyd.entity.SysLog;
import com.xyd.entity.SysUser;
import com.xyd.service.SysLogService;
import com.xyd.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/14/15:23
 * @Description:
 */
@Aspect
public class LogAspect {

    @Autowired
    SysLogService service;

    //spring容器会自动的将原生request封装成一个子类，通过注入方式注入spring管理的子类
    @Autowired
    HttpServletRequest request;

    @Pointcut(value = "within(com.xyd.controller.*Controller)")
    public void pointcut() {
    }

    //正常日志

    /**
     * 返回通知
     *
     * @param joinPoint 返回值对象
     * @param obj       连接点（方法）对象
     */
    @AfterReturning(pointcut = "pointcut()", returning = "obj")
    public void afterReturning(JoinPoint joinPoint, Object obj) {
        saveLog(joinPoint, null);
    }

    //异常日志
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        saveLog(joinPoint, e);
    }

    //统一保存处理
    private void saveLog(JoinPoint joinPoint, Exception e) {
        SysLog sysLog = new SysLog();
        sysLog.setType(e == null ? "1" : "2");//判断e是否空，如果空则是正常日志
        sysLog.setException(e == null ? "" : e.toString());
        if (request != null) {
            sysLog.setRequestUri(request.getRequestURI());
            sysLog.setRemoteAddr(IPUtils.getClientAddress(request));
            sysLog.setUserAgent(request.getHeader("User-Agent"));
            SysUser userInfo = (SysUser) request.getSession().getAttribute("userInfo");
            if (userInfo != null) {
                sysLog.setCreateBy(userInfo.getName());
            }
            sysLog.setCreateDate(new Date());
            sysLog.setMethod(request.getMethod());
        }
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                String typeName = args[i].getClass().getSimpleName();
                sb.append("[参数").append(i + 1).append(",类型:").append(typeName)
                        .append("，值:").append(args[i].toString()).append("],");

            }
            sb.deleteCharAt(sb.length() - 1);
            sysLog.setParams(sb.toString());
        }
        service.insert(sysLog);
    }


}
