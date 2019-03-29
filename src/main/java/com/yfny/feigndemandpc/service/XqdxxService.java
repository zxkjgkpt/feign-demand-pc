package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.fallback.XqdxxHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单详情Service
 * Author auto
 * Date  2019-03-28
 */
@FeignClient(value = "service-demandform", path = "/xqdxx", fallback = XqdxxHystrix.class)
public interface XqdxxService extends BaseService<XqdxxEntity> {

}
