package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.fallback.XqdFileHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 需求单附件Service
 * Author auto
 * Date  2019-04-10
 */
@FeignClient(value = "service-demandform", path = "/xqdFile", fallback = XqdFileHystrix.class)
public interface XqdFileClient extends BaseClient<XqdFileEntity> {

}
