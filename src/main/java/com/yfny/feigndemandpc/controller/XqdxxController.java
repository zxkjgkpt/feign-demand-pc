package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.BaseService;
import com.yfny.feigndemandpc.service.XqdxxService;
import com.yfny.utilscommon.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 需求单详情Controller
 * Author auto
 * Date  2019-04-02
 */
@RestController
@RequestMapping(value = "/xqdxx")
public class XqdxxController extends BaseController<XqdxxEntity> {

    @Autowired
    private XqdxxService xqdxxService;

    @Override
    public BaseService<XqdxxEntity> getBaseService() {
        return this.xqdxxService;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * @param   xqdxx    对象实体
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findXqdxxByCondition")
    @ResponseBody
    public InvokeResult findXqdxxByCondition1(@RequestBody XqdxxEntity xqdxx) throws Exception {
        List<XqdxxEntity> result = xqdxxService.findXqdxxByCondition(xqdxx);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号，分页返回
     * @param   xqdxx    对象实体
     * @param   pageNum   页数
     * @param   pageSize  每页数量
     * @return  返回对象列表为查询结果
     */
    @PostMapping(value = "/findXqdxxByCondition/{pageNum}/{pageSize}")
    @ResponseBody
    public InvokeResult findXqdxxByCondition2(@RequestBody XqdxxEntity xqdxx,
                @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception {
        List<XqdxxEntity> result = xqdxxService.findXqdxxByCondition(xqdxx, pageNum, pageSize);
        if (result != null) {
            return InvokeResult.success(result);
        }else if (result == null) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

}
