package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.fallback.QuartzHystrix;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Quartz定时任务Service
 * <p>
 * Created  by  jinboYu  on  2019/4/17
 */
@FeignClient(value = "quartz-api", path = "/quartzApi", fallback = QuartzHystrix.class)
public interface QuartzApiClient extends BaseClient {

    /**
     * 添加定时任务
     * @param xqdxxEntity   需求单实体
     * @return
     */
    @PostMapping("/job/add")
    int add(@RequestBody XqdxxEntity xqdxxEntity) ;
}
