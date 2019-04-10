package com.yfny.feigndemandpc.service;

import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.fallback.XqdFileHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单附件Service
 * Author auto
 * Date  2019-04-10
 */
@FeignClient(value = "service-demandform", path = "/xqdFile", fallback = XqdFileHystrix.class)
public interface XqdFileService extends BaseService<XqdFileEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   xqdFile    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdFileByAndCondition")
    List<XqdFileEntity> findXqdFileByAndCondition(@RequestBody XqdFileEntity xqdFile);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集，分页返回
     *
     * @param   xqdFile    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdFileByAndCondition/{pageNum}/{pageSize}")
    List<XqdFileEntity> findXqdFileByAndCondition(@RequestBody XqdFileEntity xqdFile, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param   xqdFile    对象实体
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdFileByORCondition")
    List<XqdFileEntity> findXqdFileByORCondition(@RequestBody XqdFileEntity xqdFile);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集，分页返回
     *
     * @param   xqdFile    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  对象列表
     */
    @PostMapping(value = "/findXqdFileByORCondition/{pageNum}/{pageSize}")
    List<XqdFileEntity> findXqdFileByORCondition(@RequestBody XqdFileEntity xqdFile, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize);

}
