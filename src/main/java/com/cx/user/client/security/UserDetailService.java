package com.cx.user.client.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.user.client.commom.UserDetailsModel;
import com.cx.user.entity.UserBaseInfo;
import com.cx.user.service.IAPIUserBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    private @Reference IAPIUserBaseInfoService userBaseInfoService;

    @Override
    public UserDetails loadUserByUsername(String loginName) {

        logger.info("获取{}用户信息", loginName);

        UserBaseInfo user = userBaseInfoService.getUserBaseInfoByLoginName(loginName);
        if (user == null) {
            user = userBaseInfoService.getUserBaseInfoByMobile(loginName);
        }

        if (user == null) {
            throw new UsernameNotFoundException(loginName + " not found");
        }

        if (user.getUserStatus() != 1) {
            throw new DisabledException(String.format("账号%s已经停用", loginName));
        }

        UserDetailsModel retModel = new UserDetailsModel();
        retModel.setUserBaseInfo(user);
        return retModel;
    }

}
