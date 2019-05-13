package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdFileBlocksEntity;
import com.yfny.feigndemandpc.service.XqdFileBlocksService;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

/**
 * 需求单附件Hystrix
 * Author auto
 * Date  2019-04-17
 */
@Component
public class XqdFileBlocksHystrix extends BaseHystrix<XqdFileBlocksEntity> implements XqdFileBlocksService {

}
