package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.fallback.XqdxxHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单详情Service
 * Author auto
 * Date  2019-04-03
 */
@FeignClient(value = "service-demandform", path = "/xqdxx", fallback = XqdxxHystrix.class)
public interface XqdxxService extends BaseService<XqdxxEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   xqdxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findSimpleXqdxxByAndCondition")
    List<XqdxxEntity> findSimpleXqdxxByAndCondition(@RequestBody XqdxxEntity xqdxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   xqdxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdxxByAndCondition")
    List<XqdxxEntity> findXqdxxByAndCondition(@RequestBody XqdxxEntity xqdxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集，分页返回
     *
     * @param   xqdxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdxxByAndCondition/{pageNum}/{pageSize}")
    List<XqdxxEntity> findXqdxxByAndCondition(@RequestBody XqdxxEntity xqdxx, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param   xqdxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findSimpleXqdxxByORCondition")
    List<XqdxxEntity> findSimpleXqdxxByORCondition(@RequestBody XqdxxEntity xqdxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param   xqdxx    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdxxByORCondition")
    List<XqdxxEntity> findXqdxxByORCondition(@RequestBody XqdxxEntity xqdxx);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集，分页返回
     *
     * @param   xqdxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdxxByORCondition/{pageNum}/{pageSize}")
    List<XqdxxEntity> findXqdxxByORCondition(@RequestBody XqdxxEntity xqdxx, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

}
