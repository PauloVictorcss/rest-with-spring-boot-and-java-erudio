package br.com.erudio.config;

import br.com.erudio.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration //This annotation tells that spring will find configuration regarding the application behavior
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml"); //Essa parte é para adicionar o Media type YML

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
         //below are some ways to require content negotiation
        //via EXTENSION. http://localhost:8080/api/person/v1.xml DEPRECATED on springBoot 2

       //via QUERY PARAM. http://localhost:8080/api/person/v1?mediaType=xml
        /*
        configurer.favorParameter(true)
                .parameterName("mediaType").ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML)
        */

        //via HEADER PARAM. http://localhost:8080/api/person/v1
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML)
                    .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);


    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) { //this is also necessary for yaml
        converters.add(new YamlJackson2HttpMessageConverter());
    }
}
