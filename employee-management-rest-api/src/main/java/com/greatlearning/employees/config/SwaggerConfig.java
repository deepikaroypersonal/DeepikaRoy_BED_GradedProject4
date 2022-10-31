package com.greatlearning.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	@Bean
	public Docket orderApi()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("employee-management-rest-api")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.greatlearning.employees.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() 
	 {
		return new ApiInfoBuilder()
				.title("Employee Management REST API")
				.description("Employee Management api for developers")
				.termsOfServiceUrl("http://www.c11s.com")
				.contact(new Contact("Employee Management API", "fake website url","fake email"))
				.license("license info")
				.licenseUrl("fake license url")
				.version("1.0.0")
				.build();
	}


}
