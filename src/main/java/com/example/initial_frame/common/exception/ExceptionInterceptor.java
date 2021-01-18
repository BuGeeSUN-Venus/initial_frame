package com.example.initial_frame.common.exception;

import com.example.initial_frame.common.restful.ResponseCode;
import com.example.initial_frame.common.restful.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Aspect
@Component
@Slf4j
public class ExceptionInterceptor {

    /**
     *     定义一个切点：
     *            第一个 * 代表任意修饰符及任意返回值；
     *            第二个 * 代表定义在 web 包或者子包；
     *            第三个 * 代表任意方法，.. 匹配任意数量的参数；
     */
    @Pointcut(value="execution(* com.example.initial_frame..*.*(..))")
    public void logPointcut() {

    }

    /**
     * <p>Title: doAround</p>
     * <p>Description: 环绕通知，切整个包下面的所有涉及到调用方法的信息</p>
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String msg = "";
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        }catch (SDCException sdcException){
            msg= sdcException.getMessage();
            return ResponseData.FAILED(msg,null);
        }catch (Throwable e){
            msg= e.getMessage();
            log.error(msg);
            throw e ;
        }
    }




}
