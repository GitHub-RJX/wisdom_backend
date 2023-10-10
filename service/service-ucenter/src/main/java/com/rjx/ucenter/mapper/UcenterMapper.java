package com.rjx.ucenter.mapper;

import com.rjx.ucenter.entity.Ucenter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UcenterMapper extends BaseMapper<Ucenter> {
    @Select("select count(*) from ucenter_member where DATE (gmt_create) = #{date}")
    Integer countRegisters(String date);
}
