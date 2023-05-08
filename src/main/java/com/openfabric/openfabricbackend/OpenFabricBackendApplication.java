package com.openfabric.openfabricbackend;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@SecurityScheme(name = "openfabric", scheme = "bearer",bearerFormat = "JWT",type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OpenFabricBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenFabricBackendApplication.class, args);
	}

}
