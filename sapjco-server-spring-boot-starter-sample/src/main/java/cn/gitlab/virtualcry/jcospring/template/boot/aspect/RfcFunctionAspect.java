package cn.gitlab.virtualcry.jcospring.template.boot.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Test rfc function aop
 *
 * @author VirtualCry
 */
@Component
@Aspect
public class RfcFunctionAspect {

    private static final Log logger =
            LogFactory.getLog(RfcFunctionAspect.class);


    /**
     * around enhance.
     */
    @Around("execution(* cn.gitlab.virtualcry.jcospring.connect.server.handler.FunctionInvokeHandler.handleRequest(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        logger.info("==> Just a test for aop.");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

    }
}
