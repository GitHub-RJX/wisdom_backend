package com.rjx.msm.service;

import java.util.Map;

public interface Msmservice {
    Boolean sendMsm(Map<String, Object> map, String phone);
}
