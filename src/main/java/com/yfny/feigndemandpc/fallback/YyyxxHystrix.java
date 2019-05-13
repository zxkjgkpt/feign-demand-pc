package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.YyyxxEntity;
import com.yfny.feigndemandpc.service.YyyxxClient;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

/**
 * 需求单应用域详情Hystrix
 * Author auto
 * Date  2019-04-09
 */
@Component
public class YyyxxHystrix extends BaseHystrix<YyyxxEntity> implements YyyxxClient {

}
