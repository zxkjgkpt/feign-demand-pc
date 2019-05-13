package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.YwyxxEntity;
import com.yfny.feigndemandpc.service.YwyxxClient;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

/**
 * 需求单业务域详情Hystrix
 * Author auto
 * Date  2019-04-03
 */
@Component
public class YwyxxHystrix extends BaseHystrix<YwyxxEntity> implements YwyxxClient {

}
