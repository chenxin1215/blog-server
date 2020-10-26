package com.cx.blog.server.util;

import com.cx.user.client.commom.UserDetailsModel;
import com.cx.user.entity.UserBaseInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityUtils {

    public static UserDetailsModel getPrincipal() {
        return (UserDetailsModel)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @SuppressWarnings("unchecked")
    public static boolean hasRole(String role) {
        UserDetailsModel user = getPrincipal();
        List<GrantedAuthority> auths = (List<GrantedAuthority>)user.getAuthorities();
        for (GrantedAuthority grantedAuthority : auths) {
            if (grantedAuthority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public static Long getLoginUserId() {
        UserDetailsModel user = SecurityUtils.getPrincipal();

        if (user != null) {
            UserBaseInfo userModel = user.getUserBaseInfo();
            if (userModel != null) {
                return userModel.getUserId();
            }
        }

        return null;
    }

    public static String getLoginUserName() {
        UserDetailsModel user = SecurityUtils.getPrincipal();

        if (user != null) {
            UserBaseInfo userModel = user.getUserBaseInfo();
            if (userModel != null) {
                return userModel.getLoginName();
            }
        }

        return null;
    }
}
