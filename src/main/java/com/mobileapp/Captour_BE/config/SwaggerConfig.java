package com.mobileapp.Captour_BE.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("2024 모바일 앱 프로그래밍 과제 Captour")
                        .description("2024 모바일 앱 프로그래밍 과제 Captour API 명세서")
                        .version("1.0.0"));
    }
}