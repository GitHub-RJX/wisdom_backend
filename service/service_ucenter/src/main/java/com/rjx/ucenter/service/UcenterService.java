package com.rjx.ucenter.service;

import com.rjx.ucenter.entity.Ucenter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rjx.ucenter.entity.vo.RegisterVo;

public interface UcenterService extends IService<Ucenter> {

    void register(RegisterVo registerVo);

    String login(Ucenter ucenter);

    Integer countRegisters(String date);
}
