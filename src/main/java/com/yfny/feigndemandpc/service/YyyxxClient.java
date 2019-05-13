package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.YyyxxEntity;
import com.yfny.feigndemandpc.fallback.YyyxxHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 需求单应用域详情Client
 * Author auto
 * Date  2019-04-09
 */
@FeignClient(value = "service-demandform", path = "/yyyxx", fallback = YyyxxHystrix.class)
public interface YyyxxClient extends BaseClient<YyyxxEntity> {

}
