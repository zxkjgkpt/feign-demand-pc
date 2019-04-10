package com.yfny.feigndemandpc.fallback;

import com.yfny.corepojo.entity.demandform.XqdFileEntity;
import com.yfny.feigndemandpc.service.XqdFileService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需求单附件Hystrix
 * Author auto
 * Date  2019-04-10
 */
@Component
public class XqdFileHystrix extends BaseHystrix<XqdFileEntity> implements XqdFileService {

    @Override
    public List<XqdFileEntity> findXqdFileByAndCondition(XqdFileEntity xqdFile) {
        return null;
    }

    @Override
    public List<XqdFileEntity> findXqdFileByAndCondition(XqdFileEntity xqdFile, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<XqdFileEntity> findXqdFileByORCondition(XqdFileEntity xqdFile) {
        return null;
    }

    @Override
    public List<XqdFileEntity> findXqdFileByORCondition(XqdFileEntity xqdFile, int pageNum, int pageSize) {
        return null;
    }

}
