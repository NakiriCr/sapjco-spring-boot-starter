package cn.gitlab.virtualcry.sapjco.spring.boot.demo.aspect;

import cn.gitlab.virtualcry.sapjco.spring.boot.demo.aspect.vo.SapInvokeLog;
import cn.gitlab.virtualcry.sapjco.util.data.JCoDataUtils;
import com.sap.conn.jco.JCoFunction;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static cn.gitlab.virtualcry.sapjco.spring.boot.demo.aspect.vo.AspectHandleType.AFTER;
import static cn.gitlab.virtualcry.sapjco.spring.boot.demo.aspect.vo.AspectHandleType.BEFORE;

/**
 * Test rfc function aop
 *
 * @author VirtualCry
 */
@Log4j
@Component
@Aspect
public class SapFuncCallbackAspect {

    /**
     * around enhance.
     */
    @Around("execution(* cn.gitlab.virtualcry.sapjco.server.handler.FunctionInvokeHandler.handleRequest(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final String aspectHandleId = UUID.randomUUID().toString();

        try {
            /* Before around. */
            this.argsHandle(aspectHandleId, proceedingJoinPoint);
        }catch (Exception ex) {
            log.error("Fail to handle method's args.", ex);
        }

        Object methodResult = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

        try {
            /* After around. */
            return this.returnHandle(aspectHandleId, proceedingJoinPoint, methodResult);
        }catch (Exception ex) {
            log.error("Fail to handle method's result.", ex);
        }

        return methodResult;
    }

    /* ============================================================================================================ */

    /**
     * Method args handle..
     * @param aspectHandleId aspectHandleId
     * @param proceedingJoinPoint proceedingJoinPoint
     */
    private void argsHandle(String aspectHandleId, ProceedingJoinPoint proceedingJoinPoint){

        log.info("----------------------------- Start receiving data from SAP -------------------------------");

        for (Object arg : proceedingJoinPoint.getArgs()) {
            if (arg instanceof JCoFunction) {
                SapInvokeLog invokeLog = SapInvokeLog.builder()
                        .handleId(aspectHandleId)
                        .handleTime(new Date())
                        .handleType(BEFORE)
                        .functionName(((JCoFunction) arg).getName())
                        .importParameterList(JCoDataUtils.getJCoParameterListValue(((JCoFunction) arg).getImportParameterList()))
                        .tableParameterList(JCoDataUtils.getJCoParameterListValue(((JCoFunction) arg).getTableParameterList()))
                        .exportParameterList(JCoDataUtils.getJCoParameterListValue(((JCoFunction) arg).getExportParameterList()))
                        .changingParameterList(JCoDataUtils.getJCoParameterListValue(((JCoFunction) arg).getChangingParameterList()))
                        .build();
                log.info("\n"
                        + "InvokeID             → " + invokeLog.getHandleId()               + "\n"
                        + "InvokeAspect         → " + invokeLog.getHandleType()             + "\n"
                        + "Function             → " + invokeLog.getFunctionName()           + "\n"
                        + "ImportParameterList  → " + invokeLog.getImportParameterList()    + "\n"
                        + "TableParameterList   → " + invokeLog.getTableParameterList()     + "\n"
                );
            }
        }
    }

    /**
     * Method return obj handle.
     * @param aspectHandleId aspectHandleId
     * @param proceedingJoinPoint proceedingJoinPoint
     * @param returnObject returnObject
     */
    private Object returnHandle(String aspectHandleId, ProceedingJoinPoint proceedingJoinPoint, Object returnObject){
        for (Object arg : proceedingJoinPoint.getArgs()) {
            if (arg instanceof JCoFunction) {
                SapInvokeLog invokeLog = SapInvokeLog.builder()
                        .handleId(aspectHandleId)
                        .handleTime(new Date())
                        .handleType(AFTER)
                        .functionName(((JCoFunction) arg).getName())
                        .importParameterList(JCoDataUtils.getJCoParameterListValue(((JCoFunction) arg).getImportParameterList()))
                        .tableParameterList(JCoDataUtils.getJCoParameterListValue(((JCoFunction) arg).getTableParameterList()))
                        .build();
                log.info("\n"
                        + "InvokeID             → " + invokeLog.getHandleId()               + "\n"
                        + "InvokeAspect         → " + invokeLog.getHandleType()             + "\n"
                        + "Function             → " + invokeLog.getFunctionName()           + "\n"
                        + "ExportParameterList  → " + invokeLog.getExportParameterList()    + "\n"
                        + "TableParameterList   → " + invokeLog.getTableParameterList()     + "\n"
                );
            }
        }

        log.info("------------------------------ End receiving data from SAP --------------------------------");

        return returnObject;
    }

}
