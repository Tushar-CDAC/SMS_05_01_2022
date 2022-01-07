package com.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class DemoStockManagementSystemPocApplication {
	
//	private static final Logger LOGGER=LoggerFactory.getLogger(DemoStockManagementSystemPocApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoStockManagementSystemPocApplication.class, args);
		log.info("Testing");
	}
	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.stock")).build();
	   }
}
