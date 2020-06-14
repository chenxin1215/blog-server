package com.cx.user.client.service.impl.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.user.entity.UserBaseInfo;
import com.cx.user.service.IAPIUserBaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author chenxin
 * @date 2020/6/14
 */
@Component
public class UserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Reference
    private IAPIUserBaseInfoService userBaseInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername  username = " + username);

        UserBaseInfo userBaseInfo = userBaseInfoService.getUserBaseInfoByLoginName(username);

        logger.info("登录用户名:" + username);

        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是" + password);

        return new User(username, password, true, true, true, true,
            AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}