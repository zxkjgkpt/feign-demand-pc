package com.yfny.feigndemandpc.future;

import com.yfny.corepojo.entity.demandform.YwyxxEntity;
import com.yfny.feigndemandpc.service.YwyxxClient;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 需求单业务域详情Future
 * Author auto
 * Date  2019-04-03
 */
@Component
public class YwyxxFuture extends BaseFuture<YwyxxEntity> {

    @Autowired
    private YwyxxClient ywyxxClient;

    @Override
    public BaseClient<YwyxxEntity> getBaseClient() {
        return this.ywyxxClient;
    }
}
