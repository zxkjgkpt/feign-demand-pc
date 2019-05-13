package com.yfny.feigndemandpc.future;

import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.service.XqdFileClient;
import com.yfny.utilscommon.basemvc.consumer.BaseClient;
import com.yfny.utilscommon.basemvc.consumer.BaseFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 需求单附件Future
 * Author auto
 * Date  2019-04-03
 */
@Component
public class XqdxxFileFuture extends BaseFuture<XqdFileEntity> {

    @Autowired
    private XqdFileClient xqdFileClient;

    @Override
    public BaseClient<XqdFileEntity> getBaseClient() {
        return this.xqdFileClient;
    }
}
