package com.cx.user.client.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cx.blog.server.dto.response.SimpleView;
import com.cx.blog.server.dto.response.StringView;
import com.cx.blog.server.utils.JsonUtil;
import com.cx.blog.server.utils.PrintWriterUtils;
import com.cx.blog.server.utils.exception.BizExceptionEnum;
import com.cx.user.client.commom.UserDetailsModel;
import com.cx.user.service.IAPISysMenuService;
import com.cx.user.service.IAPIUserPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@Configuration
public class LoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    private @Reference IAPISysMenuService menuService;
    private @Reference IAPIUserPermissionService permissionService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserDetailsModel detailsModel = (UserDetailsModel)authentication.getPrincipal();

        detailsModel.setSessionId(request.getSession().getId());
        detailsModel.getUserBaseInfo().setLoginPassword(null);
        Long userId = detailsModel.getUserBaseInfo().getUserId();

        // // 获取路由地址
        // List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        // List<RouterVo> routers = menuService.buildMenus(menus);
        // detailsModel.setRouters(routers);

        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        detailsModel.setRoles(roles);

        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        detailsModel.setPermissions(permissions);

        SimpleView view = new SimpleView();
        view.success(detailsModel);
        view.setRspMsg("登录成功");

        out.write(JsonUtil.stringify(view));
        out.flush();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");

        StringView resp = new StringView();

        if (exception instanceof UsernameNotFoundException) {// UsernameNotFoundException 用户找不到
            resp.fail(BizExceptionEnum.USERNAME_NOT_FOUND);
        } else if (exception instanceof BadCredentialsException) {// BadCredentialsException 坏的凭据
            resp.fail(BizExceptionEnum.ACCOUNT_NOT_MATCH);
        } else if (exception instanceof AccountExpiredException) {// AccountExpiredException 账户过期
            resp.fail("账户过期");
        } else if (exception instanceof LockedException) {// LockedException 账户锁定
            resp.fail(BizExceptionEnum.LOCKED);
        } else if (exception.getCause() instanceof DisabledException) {// DisabledException 账户不可用
            resp.fail(BizExceptionEnum.DISABLED);
        } else if (exception instanceof CredentialsExpiredException) {// CredentialsExpiredException 证书过期
            resp.fail("凭证已过期");
        } else {
            resp.fail("登录异常，请联系管理员");
            logger.error("登录异常，请联系管理员", exception);
        }

        PrintWriterUtils.flush(response, resp);
    }

}
