package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.service.XqdFileClient;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

/**
 * 需求单附件Hystrix
 * Author auto
 * Date  2019-04-10
 */
@Component
public class XqdFileHystrix extends BaseHystrix<XqdFileEntity> implements XqdFileClient {

}
