package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.YwyxxEntity;
import com.yfny.feigndemandpc.service.BaseService;
import com.yfny.feigndemandpc.service.YwyxxService;
import com.yfny.utilscommon.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单业务域详情Controller
 * Author auto
 * Date  2019-04-03
 */
@RestController
@RequestMapping(value = "/ywyxx")
public class YwyxxController extends BaseController<YwyxxEntity> {

    @Autowired
    private YwyxxService ywyxxService;

    @Override
    public BaseService<YwyxxEntity> getBaseService() {
        return this.ywyxxService;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   ywyxx    对象实体
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYwyxxByAndCondition")
    @ResponseBody
    public InvokeResult findYwyxxByAndCondition1(@RequestBody YwyxxEntity ywyxx) throws Exception {
        List<YwyxxEntity> result = ywyxxService.findYwyxxByAndCondition(ywyxx);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集，分页返回
     *
     * @param   ywyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYwyxxByAndCondition/{pageNum}/{pageSize}")
    @ResponseBody
    public InvokeResult findYwyxxByAndCondition2(@RequestBody YwyxxEntity ywyxx,
                @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        List<YwyxxEntity> result = ywyxxService.findYwyxxByAndCondition(ywyxx, pageNum, pageSize);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param   ywyxx    对象实体
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYwyxxByORCondition")
    @ResponseBody
    public InvokeResult findYwyxxByORCondition1(@RequestBody YwyxxEntity ywyxx) throws Exception {
        List<YwyxxEntity> result = ywyxxService.findYwyxxByORCondition(ywyxx);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集，分页返回
     *
     * @param   ywyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYwyxxByORCondition/{pageNum}/{pageSize}")
    @ResponseBody
    public InvokeResult findYwyxxByORCondition2(@RequestBody YwyxxEntity ywyxx,
                @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        List<YwyxxEntity> result = ywyxxService.findYwyxxByORCondition(ywyxx, pageNum, pageSize);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

}
