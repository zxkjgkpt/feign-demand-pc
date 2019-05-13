package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdFileBlocksEntity;
import com.yfny.feigndemandpc.fallback.XqdFileBlocksHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 需求单附件Service
 * Author auto
 * Date  2019-04-17
 */
@FeignClient(value = "service-demandform", path = "/xqdFileBlocks", fallback = XqdFileBlocksHystrix.class)
public interface XqdFileBlocksService extends BaseClient<XqdFileBlocksEntity> {

}
