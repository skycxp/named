package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.bean.NamedReq;
import com.tencent.wxcloudrun.bean.NamedRes;

public interface NamedService {
    NamedRes getName(NamedReq namedReq);
}
