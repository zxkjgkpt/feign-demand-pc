package com.yfny.feigndemandpc.future;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.XqdxxClient;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 需求单详情Future
 * Author auto
 * Date  2019-04-03
 */
@Component
public class XqdxxFuture extends BaseFuture<XqdxxEntity> {

    @Autowired
    private XqdxxClient xqdxxClient;

    @Override
    public BaseClient<XqdxxEntity> getBaseClient() {
        return this.xqdxxClient;
    }
}
