package com.yfny.feigndemandpc.aspect.after;

import com.ydtf.common.tool.Tools;
import com.yfny.corepojo.entity.demandform.XqdFlowLogEntity;
import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.future.XqdFlowLogFuture;
import com.yfny.utilscommon.util.InvokeResult;
import com.yfny.utilscommon.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jisongZhou on 2019/5/8.
 **/
@Aspect
@Component
public class AfterReturning {

    private final static int SUBMIT = 1;
    private final static int AUDIT = 2;

    @Autowired
    private XqdFlowLogFuture xqdFlowLogFuture;

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

    @org.aspectj.lang.annotation.AfterReturning(pointcut = "execution(public * com.yfny.feigndemandpc.controller.XqdxxController.submitXqd(..))",
            returning = "result")
    public void afterSubmitXqd(JoinPoint joinPoint, InvokeResult result) {
        if ("10001".equals(result.getCode())) {
            XqdFlowLogEntity xqdFlowLogEntity = constructXqd(joinPoint, result, SUBMIT);
            xqdFlowLogFuture.insertSelective(xqdFlowLogEntity);
        }
    }

    @org.aspectj.lang.annotation.AfterReturning(pointcut = "execution(public * com.yfny.feigndemandpc.controller.XqdxxController.auditXqd(..))",
            returning = "result")
    public void afterAuditXqd(JoinPoint joinPoint, InvokeResult result) {
        if ("10001".equals(result.getCode())) {
            XqdFlowLogEntity xqdFlowLogEntity = constructXqd(joinPoint, result, AUDIT);
            xqdFlowLogFuture.insertSelective(xqdFlowLogEntity);
        }
    }

    private XqdFlowLogEntity constructXqd(JoinPoint joinPoint, InvokeResult result, int type) {
        //获取参数
        Object[] args = joinPoint.getArgs();
        XqdxxEntity param = (XqdxxEntity) args[0];
        XqdxxEntity resultData = (XqdxxEntity) result.getData();

        String dh = param.getXqdh();
        String clrid = "efa04337d0f548f1b7a264b521ff9a0d";
        String clrmc = "系统管理员";
        String rzjl = "";
        if (StringUtils.isNotBlank(param.getShyj())) {
            rzjl = param.getShyj();
        }else if (StringUtils.isNotBlank(param.getSshyj())) {
            rzjl = param.getSshyj();
        }else if (StringUtils.isNotBlank(param.getWshyj())) {
            rzjl = param.getWshyj();
        }
        String zyyy = param.getZfyy();

        String gdzt = "";
        switch (type) {
            case SUBMIT:
                gdzt = "提交";
        	    break;
            case AUDIT:
                if ("cancelXqd".equals(param.getGateway())) {
                    gdzt = "申请作废";
                }else {
                    gdzt = resultData.getGdzt();
                }
                break;
            default:
        	    break;
        }

        XqdFlowLogEntity xqdFlowLogEntity = new XqdFlowLogEntity();
        xqdFlowLogEntity.setDh(dh);
        xqdFlowLogEntity.setLx((short) 1);
        xqdFlowLogEntity.setGdzt(gdzt);
        xqdFlowLogEntity.setJssj(Tools.getDateTime("yyyy-MM-dd HH:mm:ss"));
        xqdFlowLogEntity.setClrid(clrid);
        xqdFlowLogEntity.setClrmc(clrmc);
        xqdFlowLogEntity.setRzjl(rzjl);
        xqdFlowLogEntity.setZfyy(zyyy);

        return xqdFlowLogEntity;
    }

}
