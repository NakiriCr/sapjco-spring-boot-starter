package org.yanzx.template.sap.server.bootstrap.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Func callback aspect
 *
 * @author VirtualCry
 */
@Component
@Aspect
public class CallbackFuncAspect {
    private static final Log logger = LogFactory.getLog(CallbackFuncAspect.class);

    /**
     * around enhance.
     */
    @Around("execution(* cn.gitlab.virtualcry.jcospring.connect.server.handler.FunctionInvokeHandler.handleRequest(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("==> Just a test for aop.");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }
}
