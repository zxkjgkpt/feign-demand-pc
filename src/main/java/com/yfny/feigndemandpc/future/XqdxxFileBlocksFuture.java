package com.yfny.feigndemandpc.future;

import com.yfny.corepojo.entity.demandform.XqdFileBlocksEntity;
import com.yfny.feigndemandpc.service.XqdFileBlocksService;
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
    public class XqdxxFileBlocksFuture extends BaseFuture<XqdFileBlocksEntity> {

    @Autowired
    private XqdFileBlocksService xqdFileBlocksService;

    @Override
    public BaseClient<XqdFileBlocksEntity> getBaseClient() {
        return this.xqdFileBlocksService;
    }


}
