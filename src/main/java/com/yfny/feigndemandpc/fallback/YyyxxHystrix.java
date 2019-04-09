package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.YyyxxEntity;
import com.yfny.feigndemandpc.service.YyyxxService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需求单应用域详情Hystrix
 * Author auto
 * Date  2019-04-09
 */
@Component
public class YyyxxHystrix extends BaseHystrix<YyyxxEntity> implements YyyxxService {

    @Override
    public List<YyyxxEntity> findYyyxxByAndCondition(YyyxxEntity yyyxx) {
        return null;
    }

    @Override
    public List<YyyxxEntity> findYyyxxByAndCondition(YyyxxEntity yyyxx, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<YyyxxEntity> findYyyxxByORCondition(YyyxxEntity yyyxx) {
        return null;
    }

    @Override
    public List<YyyxxEntity> findYyyxxByORCondition(YyyxxEntity yyyxx, int pageNum, int pageSize) {
        return null;
    }

}
