package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.XqdxxClient;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

/**
 * 需求单详情Hystrix
 * Author auto
 * Date  2019-04-03
 */
@Component
public class XqdxxHystrix extends BaseHystrix<XqdxxEntity> implements XqdxxClient {

}
