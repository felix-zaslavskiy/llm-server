package com.hexadevlabs.llmserver;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter
@Setter
public class YAMLConfig {

    private  String modelFilename;

    private  String modelBasePath;

    // standard getters and setters

}