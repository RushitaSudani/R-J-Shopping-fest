package org.technous.validation.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Ecommerce API", description = "This is Project ", summary = "this is api for add update delete and Create", termsOfService = "R&j", contact = @Contact(name = "Tisha Sudani", email = "tishasudani@gmail.com"), license = @License(name = "there is no license"), version = "v1"), servers = {@Server(description = "dev", url = "http://localhost:8091"), @Server(description = "Test", url = "http://localhost:8091")})
@SecurityScheme(name = "auntBear", in = SecuritySchemeIn.HEADER, bearerFormat = "JWT", type = SecuritySchemeType.HTTP)
public class OpenApIConfig {
}
