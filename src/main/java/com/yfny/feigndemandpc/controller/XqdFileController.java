package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.service.BaseService;
import com.yfny.feigndemandpc.service.XqdFileService;
import com.yfny.utilscommon.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单附件Controller
 * Author auto
 * Date  2019-04-10
 */
@RestController
@RequestMapping(value = "/xqdFile")
public class XqdFileController extends BaseController<XqdFileEntity> {

    @Autowired
    private XqdFileService xqdFileService;

    @Override
    public BaseService<XqdFileEntity> getBaseService() {
        return this.xqdFileService;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   xqdFile    对象实体
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findXqdFileByAndCondition")
    @ResponseBody
    public InvokeResult findXqdFileByAndCondition1(@RequestBody XqdFileEntity xqdFile) throws Exception {
        List<XqdFileEntity> result = xqdFileService.findXqdFileByAndCondition(xqdFile);
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
     * @param   xqdFile    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findXqdFileByAndCondition/{pageNum}/{pageSize}")
    @ResponseBody
    public InvokeResult findXqdFileByAndCondition2(@RequestBody XqdFileEntity xqdFile,
                @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        List<XqdFileEntity> result = xqdFileService.findXqdFileByAndCondition(xqdFile, pageNum, pageSize);
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
     * @param   xqdFile    对象实体
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findXqdFileByORCondition")
    @ResponseBody
    public InvokeResult findXqdFileByORCondition1(@RequestBody XqdFileEntity xqdFile) throws Exception {
        List<XqdFileEntity> result = xqdFileService.findXqdFileByORCondition(xqdFile);
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
     * @param   xqdFile    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findXqdFileByORCondition/{pageNum}/{pageSize}")
    @ResponseBody
    public InvokeResult findXqdFileByORCondition2(@RequestBody XqdFileEntity xqdFile,
                @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        List<XqdFileEntity> result = xqdFileService.findXqdFileByORCondition(xqdFile, pageNum, pageSize);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

}
