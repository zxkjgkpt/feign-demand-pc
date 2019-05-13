package com.yfny.feigndemandpc.aspect.before;

import com.yfny.utilscommon.util.InvokeResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by jisongZhou on 2019/5/8.
 **/
@Aspect
public class BeforeReturning {

    /**
     * 前置通知
     */
    @Before(value = "execution(public * com.yfny.feigndemandpc.controller.*.*.*(..))")
    public void doBefore() {

    }

    /**
     * 后置通知
     *
     * @param jp
     */
    @org.aspectj.lang.annotation.AfterReturning(pointcut = "execution(public * com.yfny.feigndemandpc.controller.*.*.*(..))", returning = "result")
    public void doAfter(JoinPoint jp, InvokeResult result) {

    }

}
