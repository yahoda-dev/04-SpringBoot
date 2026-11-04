package com.yahodadev.restapi.config;

import aQute.bnd.annotation.jpms.Open;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@OpenAPIDefinition(
//        info = @Info(title = "REST API 사용설명서",
//                description = "REST API 수업 자료입니다.",
//                version = "v1",
//                contact = @Contact(
//                        name = "jiwon",
//                        email = "stlee0926@naver.com"
//                ))
//)
//@Configuration
//public class SwaggerConfig {
//
//}

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {

        return new Info()
                .title("REST API 설명서")
                .description("REST API 설명서 Swagger 문서입니다.")
                .version("1.0.0");
    }
}