package com.example.restfulservices2.restfulservices2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
    @EnableSwagger2
    public class SwaggerConfig {

    /*public static final Contact DEFAULT_CONTACT = new Contact(
            "Suraj Dubey","www.tothenew.com","suraj.dubey@tothenew.com"
    );
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "API Title", "API Description", "1.0",
            "urn:tos", DEFAULT_CONTACT.toString(),
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
*/
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
    new HashSet<String>(Arrays.asList("application/json","application/xml"));
    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES);

    }*/
    public static final Contact DEFAULT_CONTACT = new Contact(
            "Suraj Dubey","www.tothenew.com","suraj.dubey@tothenew.com"
    );
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "API Title", "API Description", "1.0",
            "urn:tos", DEFAULT_CONTACT.toString(),
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
    /*private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("User Details")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }*/
}

