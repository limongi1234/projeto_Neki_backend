package br.org.neki.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig 
{
	
	@Bean
	public Docket api() 
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.org.serratec.backend.controller"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
		
	}
	
	private ApiInfo apiInfo()
	{
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title(" API DE TESTE ECOMMERCE")
				.description("Essa é uma API de testes de Ecommerce")
				.license("Apache Licence version 2.0")
				.licenseUrl("https://www.apache.org/licence")
				.version("1.0.0")
				.contact(new Contact("Serratec", "www.serratec.org.br", "serratec@gmail.com"))
				.build();
		return apiInfo;
	}
}
