package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 关闭 CSRF，方便 Postman 测试
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll() // 允许注册接口不登录访问
                        .anyRequest().authenticated() // 其他接口需要认证
                )
                .httpBasic(Customizer.withDefaults()); // 使用 Basic Auth（默认配置）

        return http.build();
    }
}
