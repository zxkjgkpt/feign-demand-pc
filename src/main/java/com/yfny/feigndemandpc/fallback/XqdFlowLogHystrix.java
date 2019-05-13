package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdFlowLogEntity;
import com.yfny.feigndemandpc.service.XqdFlowLogClient;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

/**
 * 需求单操作记录Hystrix
 * Author auto
 * Date  2019-05-08
 */
@Component
public class XqdFlowLogHystrix extends BaseHystrix<XqdFlowLogEntity> implements XqdFlowLogClient {

}
