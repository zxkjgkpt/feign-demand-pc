package com.yfny.feigndemandpc.future;

import com.yfny.corepojo.entity.demandform.XqdFlowLogEntity;
import com.yfny.feigndemandpc.service.XqdFlowLogClient;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 需求单操作记录Future
 * Author auto
 * Date  2019-05-08
 */
@Component
public class XqdFlowLogFuture extends BaseFuture<XqdFlowLogEntity> {

    @Autowired
    private XqdFlowLogClient xqdFlowLogClient;

    @Override
    public BaseClient<XqdFlowLogEntity> getBaseClient() {
        return this.xqdFlowLogClient;
    }

}
