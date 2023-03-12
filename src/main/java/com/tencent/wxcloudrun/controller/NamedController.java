package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.bean.NamedReq;
import com.tencent.wxcloudrun.bean.NamedRes;
import com.tencent.wxcloudrun.service.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NamedController {

    @Autowired
    private NamedService namedService;

    @RequestMapping("/name")
    public NamedRes name(NamedReq namedReq){
        return namedService.getName(namedReq);
    }
}
