package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.YyyxxEntity;
import com.yfny.feigndemandpc.fallback.YyyxxHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单应用域详情Service
 * Author auto
 * Date  2019-04-09
 */
@FeignClient(value = "service-demandform", path = "/yyyxx", fallback = YyyxxHystrix.class)
public interface YyyxxService extends BaseService<YyyxxEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   yyyxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findYyyxxByAndCondition")
    List<YyyxxEntity> findYyyxxByAndCondition(@RequestBody YyyxxEntity yyyxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集，分页返回
     *
     * @param   yyyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findYyyxxByAndCondition/{pageNum}/{pageSize}")
    List<YyyxxEntity> findYyyxxByAndCondition(@RequestBody YyyxxEntity yyyxx, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param   yyyxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findYyyxxByORCondition")
    List<YyyxxEntity> findYyyxxByORCondition(@RequestBody YyyxxEntity yyyxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集，分页返回
     *
     * @param   yyyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findYyyxxByORCondition/{pageNum}/{pageSize}")
    List<YyyxxEntity> findYyyxxByORCondition(@RequestBody YyyxxEntity yyyxx, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

}
