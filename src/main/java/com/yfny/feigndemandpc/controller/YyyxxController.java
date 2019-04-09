package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.YyyxxEntity;
import com.yfny.feigndemandpc.service.BaseService;
import com.yfny.feigndemandpc.service.YyyxxService;
import com.yfny.utilscommon.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单应用域详情Controller
 * Author auto
 * Date  2019-04-09
 */
@RestController
@RequestMapping(value = "/yyyxx")
public class YyyxxController extends BaseController<YyyxxEntity> {

    @Autowired
    private YyyxxService yyyxxService;

    @Override
    public BaseService<YyyxxEntity> getBaseService() {
        return this.yyyxxService;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   yyyxx    对象实体
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYyyxxByAndCondition")
    @ResponseBody
    public InvokeResult findYyyxxByAndCondition1(@RequestBody YyyxxEntity yyyxx) throws Exception {
        List<YyyxxEntity> result = yyyxxService.findYyyxxByAndCondition(yyyxx);
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
     * @param   yyyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYyyxxByAndCondition/{pageNum}/{pageSize}")
    @ResponseBody
    public InvokeResult findYyyxxByAndCondition2(@RequestBody YyyxxEntity yyyxx,
                @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        List<YyyxxEntity> result = yyyxxService.findYyyxxByAndCondition(yyyxx, pageNum, pageSize);
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
     * @param   yyyxx    对象实体
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYyyxxByORCondition")
    @ResponseBody
    public InvokeResult findYyyxxByORCondition1(@RequestBody YyyxxEntity yyyxx) throws Exception {
        List<YyyxxEntity> result = yyyxxService.findYyyxxByORCondition(yyyxx);
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
     * @param   yyyxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findYyyxxByORCondition/{pageNum}/{pageSize}")
    @ResponseBody
    public InvokeResult findYyyxxByORCondition2(@RequestBody YyyxxEntity yyyxx,
                @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        List<YyyxxEntity> result = yyyxxService.findYyyxxByORCondition(yyyxx, pageNum, pageSize);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

}
