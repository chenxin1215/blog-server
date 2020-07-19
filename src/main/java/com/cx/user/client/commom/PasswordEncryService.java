package com.cx.user.client.commom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryService implements PasswordEncoder {

    // 设置私钥，防止彩虹笔获取明文密码
    private static final CharSequence SECRET = "7hD69&iJ7h";

    @Bean
    private Pbkdf2PasswordEncoder getEncoder() {
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(SECRET);
        encoder.setEncodeHashAsBase64(true);

        return encoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        Pbkdf2PasswordEncoder encoder = getEncoder();
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Pbkdf2PasswordEncoder encoder = getEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }

}
