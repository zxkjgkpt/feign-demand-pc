package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.YwyxxEntity;
import com.yfny.feigndemandpc.fallback.YwyxxHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 需求单业务域详情Service
 * Author auto
 * Date  2019-04-03
 */
@FeignClient(value = "service-demandform", path = "/ywyxx", fallback = YwyxxHystrix.class)
public interface YwyxxClient extends BaseClient<YwyxxEntity> {

}
