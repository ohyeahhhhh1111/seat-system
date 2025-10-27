package com.example.seatsystem.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS（跨來源資源共享）設定類別。
 * 
 * 允許前端（例如：React、Vue、Angular 等）從不同網域或埠號向後端 API 發送請求，
 * 避免瀏覽器出現「跨來源請求被封鎖（CORS error）」的問題。
 */

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允許所有 API 路徑
                        .allowedOrigins("*") // 允許所有網域
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允許的 HTTP 方法
                        .allowedHeaders("*"); // 允許所有的請求標頭
            }
        };
    }
}
