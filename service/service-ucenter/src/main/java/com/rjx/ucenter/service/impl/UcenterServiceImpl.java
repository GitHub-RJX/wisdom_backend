package com.rjx.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rjx.ucenter.entity.Ucenter;
import com.rjx.ucenter.entity.vo.RegisterVo;
import com.rjx.ucenter.mapper.UcenterMapper;
import com.rjx.ucenter.service.UcenterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjx.utils.JwtUtils;
import com.rjx.utils.MD5;
import com.rjx.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UcenterServiceImpl extends ServiceImpl<UcenterMapper, Ucenter> implements UcenterService {

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String mobile = registerVo.getMobile();
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password) || StringUtils.isEmpty(mobile)) {
            try {
                throw new Exception("信息填写不完整！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        LambdaQueryWrapper<Ucenter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ucenter::getMobile, registerVo.getMobile());
        Long count = baseMapper.selectCount(queryWrapper);
        if (count != 0) {
            try {
                throw new Exception("该号码已被注册！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Ucenter ucenter = new Ucenter();
        ucenter.setMobile(mobile);
        ucenter.setNickname(nickname);
        ucenter.setPassword(MD5.encrypt(password));
        ucenter.setAvatar("https://edu-longyang.oss-cn-beijing.aliyuncs.com/fa104ef58c4e5bc4270d911da1d1b34d.jpg");
//        ucenter.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(ucenter);
    }

    @Override
    public String login(Ucenter ucenter) {
        String mobile = ucenter.getMobile();
        String password = ucenter.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            try {
                throw new Exception("信息填写不完整！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        LambdaQueryWrapper<Ucenter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ucenter::getMobile, ucenter.getMobile());
        Ucenter ucenterOne = baseMapper.selectOne(queryWrapper);
        if (ucenterOne == null) {
            try {
                throw new Exception("手机号不存在！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (!MD5.encrypt(password).equals(ucenterOne.getPassword())) {
            try {
                throw new Exception("密码有误！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (ucenterOne.getIsDisabled()) {
            try {
                throw new Exception("该用户已被禁用！");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        String token = JwtUtils.getJwtToken(ucenterOne.getId(), ucenterOne.getNickname());
        return token;
    }

    @Override
    public Integer countRegisters(String date) {
        Integer num = baseMapper.countRegisters(date);
        return num;
    }
}
