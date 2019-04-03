package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.XqdxxService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需求单详情Hystrix
 * Author auto
 * Date  2019-04-03
 */
@Component
public class XqdxxHystrix extends BaseHystrix<XqdxxEntity> implements XqdxxService {

    @Override
    public List<XqdxxEntity> findXqdxxByAndCondition(XqdxxEntity xqdxx) {
        return null;
    }

    @Override
    public List<XqdxxEntity> findXqdxxByAndCondition(XqdxxEntity xqdxx, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<XqdxxEntity> findXqdxxByORCondition(XqdxxEntity xqdxx) {
        return null;
    }

    @Override
    public List<XqdxxEntity> findXqdxxByORCondition(XqdxxEntity xqdxx, int pageNum, int pageSize) {
        return null;
    }

}
