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

    // Should print info about LLM input/outputs to standard output.
    private Boolean llmDebugMode;

}