package com.totoro.service.impl;

import com.totoro.constants.Result;
import com.totoro.pojo.auth.LoginBody;
import com.totoro.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {
    @Override
    public Result register(LoginBody loginBody) {
        return null;
    }

    @Override
    public Result active(String code) {
        return null;
    }
}
