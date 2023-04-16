package com.data.user.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dto.LoginDto;

public interface ApUsrService {
    //用户登录
    ResponseResult userLogin(LoginDto loginDto);
}
