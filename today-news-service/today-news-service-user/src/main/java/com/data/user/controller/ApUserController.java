package com.data.user.controller;

import com.data.user.service.ApUsrService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApUserController {

    @Autowired
    private ApUsrService apUsrService;

    @PostMapping("/login/login_auth")       //用户登录
    public ResponseResult userLogin(@RequestBody LoginDto loginDto){
        ResponseResult responseResult = apUsrService.userLogin(loginDto);
        return responseResult;
    }

    @GetMapping("/test")       //用户登录
    public void test(@RequestBody LoginDto loginDto){
        System.out.println("------------------------");
    }
}
