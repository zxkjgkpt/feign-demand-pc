package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.QuartzApiClient;
import com.yfny.utilscommon.basemvc.consumer.BaseHystrix;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Created  by  jinboYu  on  2019/4/17
 */
@Component
public class QuartzHystrix  extends BaseHystrix implements QuartzApiClient {

    /**
     * 添加定时任务
     *
     * @param xqdxxEntity 需求单实体
     * @return
     */
    @Override
    public int add(XqdxxEntity xqdxxEntity) {
        return -1;
    }
}
