package com.data.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.user.pojo.ApUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {
}
