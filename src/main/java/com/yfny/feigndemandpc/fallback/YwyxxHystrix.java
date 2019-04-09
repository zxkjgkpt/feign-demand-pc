package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.YwyxxEntity;
import com.yfny.feigndemandpc.service.YwyxxService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需求单业务域详情Hystrix
 * Author auto
 * Date  2019-04-03
 */
@Component
public class YwyxxHystrix extends BaseHystrix<YwyxxEntity> implements YwyxxService {

    @Override
    public List<YwyxxEntity> findYwyxxByAndCondition(YwyxxEntity ywyxx) {
        return null;
    }

    @Override
    public List<YwyxxEntity> findYwyxxByAndCondition(YwyxxEntity ywyxx, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<YwyxxEntity> findYwyxxByORCondition(YwyxxEntity ywyxx) {
        return null;
    }

    @Override
    public List<YwyxxEntity> findYwyxxByORCondition(YwyxxEntity ywyxx, int pageNum, int pageSize) {
        return null;
    }

}
