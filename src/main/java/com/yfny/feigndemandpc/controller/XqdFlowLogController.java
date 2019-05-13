package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.XqdFlowLogEntity;
import com.yfny.feigndemandpc.future.XqdFlowLogFuture;
import com.yfny.utilscommon.basemvc.consumer.BaseController;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需求单操作记录Controller
 * Author auto
 * Date  2019-05-08
 */
@RestController
@RequestMapping(value = "/xqdFlowLog")
public class XqdFlowLogController extends BaseController<XqdFlowLogEntity> {

    @Autowired
    private XqdFlowLogFuture xqdFlowLogFuture;

    @Override
    public BaseFuture<XqdFlowLogEntity> getBaseFuture() {
        return this.xqdFlowLogFuture;
    }

}
