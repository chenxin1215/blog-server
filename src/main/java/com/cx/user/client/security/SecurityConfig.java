package com.cx.user.client.security;

import com.cx.user.client.commom.PasswordEncryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginHandler loginHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private PasswordEncryService passwordEncryService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private LoginAuthenticationEntryPoint loginAuthenticationEntryPoint;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    // @Autowired
    // private ValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncryService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);

        // 开启授权认证
        // 开启跨域共享，关闭同源策略【允许跨域】
        // 跨域伪造请求=无效，
        http.cors().and().csrf().disable();

        // 登录配置
        http.formLogin().loginPage("/index.html").loginProcessingUrl("/login") // 设置登录页面
            .successHandler(loginHandler) // 设置登录成功的处理器
            .failureHandler(loginHandler); // 设置登录失败的处理器;

        // 登出配置
        http.logout().logoutUrl("/logout").deleteCookies("JSESSIONID").clearAuthentication(true)
            .logoutSuccessHandler(logoutHandler).permitAll();

        // 记住我配置
        http.rememberMe().rememberMeCookieName("remember").tokenValiditySeconds(3600);

        // 异常处理配置
        http.exceptionHandling().authenticationEntryPoint(loginAuthenticationEntryPoint)
            .accessDeniedHandler(authenticationFailureHandler);

        // 权限配置
        http.authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
            .antMatchers("/index.html", "/captcha/*").permitAll() // 设置所有人都可以访问登录页面
            .anyRequest() // 任何请求,登录后可以访问
            .authenticated();

        // 以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/**", "/webjars/**", "/static/**",
            "/css/**", "/images/**");
    }
}
