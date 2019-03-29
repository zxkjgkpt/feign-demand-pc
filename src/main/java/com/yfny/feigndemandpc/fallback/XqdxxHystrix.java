package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.XqdxxService;
import org.springframework.stereotype.Component;

/**
 * 需求单详情Hystrix
 * Author auto
 * Date  2019-03-28
 */
@Component
public class XqdxxHystrix extends BaseHystrix<XqdxxEntity> implements XqdxxService {

}
