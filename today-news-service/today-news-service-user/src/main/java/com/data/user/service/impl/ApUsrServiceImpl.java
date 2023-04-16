package com.data.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.user.dao.ApUserMapper;
import com.data.user.service.ApUsrService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.pojo.ApUser;
import com.heima.utils.common.AppJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;

@Service
public class ApUsrServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUsrService {

    //用户登录
    public ResponseResult userLogin(LoginDto loginDto) {
        HashMap<String, Object> result = new HashMap<>();
        String token = "";
        if (loginDto.getPhone() == null) {
            //游客登录
            token = AppJwtUtil.getToken(0l);
        } else {
            //用户登录
            ApUser apUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, loginDto.getPhone()));
            if (apUser != null) {
                //用户存在，校验密码
                String dtoPassword = loginDto.getPassword();
                String salt = apUser.getSalt();
                dtoPassword = DigestUtils.md5DigestAsHex((dtoPassword + salt).getBytes());
                if (!apUser.getPassword().equals(dtoPassword)) {
                    //密码错误 直接返回
                    return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
                }
                //添加token
                token = AppJwtUtil.getToken(apUser.getId().longValue());
                apUser.setPassword("");
                apUser.setSalt("");
                result.put("user", apUser);
            }
            result.put("token", token);
        }
        return ResponseResult.okResult(result);
    }
}
