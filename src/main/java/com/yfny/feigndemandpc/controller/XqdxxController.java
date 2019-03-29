package com.yfny.feigndemandpc.controller;

import com.yfny.corepojo.entity.demandform.XqdxxEntity;
import com.yfny.feigndemandpc.service.BaseService;
import com.yfny.feigndemandpc.service.XqdxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 需求单详情Controller
 * Author auto
 * Date  2019-03-28
 */
@RestController
@RequestMapping(value = "/xqdxx")
public class XqdxxController extends BaseController<XqdxxEntity> {

    @Autowired
    private XqdxxService xqdxxService;

    @Override
    public BaseService<XqdxxEntity> getBaseService() {
        return this.xqdxxService;
    }

}
