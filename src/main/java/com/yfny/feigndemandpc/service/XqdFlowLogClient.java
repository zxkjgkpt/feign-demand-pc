package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdFlowLogEntity;
import com.yfny.feigndemandpc.fallback.XqdFlowLogHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 需求单操作记录Service
 * Author auto
 * Date  2019-05-08
 */
@FeignClient(value = "service-demandform", path = "/xqdFlowLog", fallback = XqdFlowLogHystrix.class)
public interface XqdFlowLogClient extends BaseClient<XqdFlowLogEntity> {

}
