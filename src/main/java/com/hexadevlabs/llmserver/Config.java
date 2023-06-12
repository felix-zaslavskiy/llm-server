package com.hexadevlabs.llmserver;

import com.hexadevlabs.gpt4all.LLModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class Config {

    @Autowired
    YAMLConfig yamlConfig;

    @Bean
    LLModelService llModelService(){
        LLModelService llModelService = new LLModelService(getLLModel());
        llModelService.setLlmDebugMode(yamlConfig.getLlmDebugMode());
        return llModelService;
    }

    @Bean
    LLModel getLLModel(){
        Path modelPath = Path.of(yamlConfig.getModelBasePath() + yamlConfig.getModelFilename());
        return new LLModel(modelPath);
    }
}
