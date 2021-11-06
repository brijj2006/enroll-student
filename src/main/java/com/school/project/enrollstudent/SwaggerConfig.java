package com.school.project.enrollstudent;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@Component
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Brijendra Singh", "https://github.com/brijj2006/enroll-student", "brijj2006@gmail.com");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Student Enrollment API Doc", "Api Documentation for student enrollment API's", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    public static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).consumes(DEFAULT_PRODUCES_CONSUMES).
                produces(DEFAULT_PRODUCES_CONSUMES);
    }
}
