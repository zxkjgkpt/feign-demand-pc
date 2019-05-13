package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.fallback.XqdxxHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 需求单详情Service
 * Author auto
 * Date  2019-04-03
 */
@FeignClient(value = "service-demandform", path = "/xqdxx", fallback = XqdxxHystrix.class)
public interface XqdxxClient extends BaseClient<XqdxxEntity> {

}
