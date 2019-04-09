package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.YwyxxEntity;
import com.yfny.feigndemandpc.fallback.YwyxxHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单业务域详情Service
 * Author auto
 * Date  2019-04-03
 */
@FeignClient(value = "service-demandform", path = "/ywyxx", fallback = YwyxxHystrix.class)
public interface YwyxxService extends BaseService<YwyxxEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   ywyxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findYwyxxByAndCondition")
    List<YwyxxEntity> findYwyxxByAndCondition(@RequestBody YwyxxEntity ywyxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集，分页返回
     *
     * @param   ywyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findYwyxxByAndCondition/{pageNum}/{pageSize}")
    List<YwyxxEntity> findYwyxxByAndCondition(@RequestBody YwyxxEntity ywyxx, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param   ywyxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findYwyxxByORCondition")
    List<YwyxxEntity> findYwyxxByORCondition(@RequestBody YwyxxEntity ywyxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集，分页返回
     *
     * @param   ywyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findYwyxxByORCondition/{pageNum}/{pageSize}")
    List<YwyxxEntity> findYwyxxByORCondition(@RequestBody YwyxxEntity ywyxx, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

}
