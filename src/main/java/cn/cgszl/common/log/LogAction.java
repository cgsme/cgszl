package cn.cgszl.common.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.cgszl.admin.service.LogService;
import cn.cgszl.common.constant.WebConst;
import cn.cgszl.common.dao.pojo.Log;
import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.utils.CgszlUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * aop日志管理
 *
 * @author cguisheng 2018/4/5 16:15
 */
@Aspect
public class LogAction {

    //注入service,用来将日志信息保存在数据库
    @Resource
    private LogService logService;

    // 配置接入点
    @Pointcut("execution(* cn.cgszl..*.*(..))")
    private void controllerAspect() {

    }

    // 定义一个切入点
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        // 日志实体对象
        Log log = new Log();

        // 方法通知前获取时间 用来计算模块执行时间的
        long start = System.currentTimeMillis();

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();

        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();

        // 拦截的方法参数
//        Object[] args = pjp.getArgs();

        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature methodSignature;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) sig;
        Class[] parameterTypes = methodSignature.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException | SecurityException e1) {
            // TODO
            e1.printStackTrace();
        }
        if (null != method) {
            // 判断是否包含自定义的注解，SystemLog是自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                log.setModule(systemlog.module());
                log.setAction(systemlog.methods());
                try {
                    // 执行
                    object = pjp.proceed();

                    // 获取登录用户账户
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    User user = (User) request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);

                    if (null != user) {
                        // 获取操作人员
                        log.setOperUserName(user.getScreenName());
                        log.setOperUserId(user.getUid());
                    }

                    // 获取系统时间
                    String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
                    log.setCreated(time);

                    // 获取系统ip
                    String ip = CgszlUtils.getClientIPAddress(request);
                    log.setIp(ip);

                    long end = System.currentTimeMillis();
                    // 将计算好的时间保存在实体中
                    log.setProcessTime((int)(end-start));
                    log.setResultCn("操作成功");
                    log.setResult(true);
                    // 保存进数据库
                    logService.saveLog(log);
                } catch (Throwable e) {
                    // TODO
                    long end = System.currentTimeMillis();
                    log.setProcessTime((int)(end-start));
                    log.setResult(false);
                    log.setResultCn("操作失败");
                    logService.saveLog(log);
                }
            } else {// 没有包含注解
                object = pjp.proceed();
            }
        } else { // 不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }
}
