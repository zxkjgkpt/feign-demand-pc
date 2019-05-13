package com.yfny.feigndemandpc.future;

import com.yfny.corepojo.entity.demandform.YyyxxEntity;
import com.yfny.feigndemandpc.service.YyyxxClient;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 需求单应用域详情Future
 * Author auto
 * Date  2019-04-03
 */
@Component
public class YyyxxFuture extends BaseFuture<YyyxxEntity> {

    @Autowired
    private YyyxxClient yyyxxClient;

    @Override
    public BaseClient<YyyxxEntity> getBaseClient() {
        return this.yyyxxClient;
    }
}
