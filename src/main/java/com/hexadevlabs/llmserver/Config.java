package com.hexadevlabs.llmserver;

import com.hexadevlabs.gpt4all.LLModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class Config {

    @Autowired
    YAMLConfig yamlConfig;

    @Bean
    LLModelService llModelService(){

        // Alt path for WSL Linux
        //String baseModelPath = "/mnt/c/Users/felix/AppData/Local/nomic.ai/GPT4All/";

        // Optionally in case override to location of shared libraries is necessary
        //LLModel.LIBRARY_SEARCH_PATH = "C:\\Users\\felix\\gpt4all\\lib\\";

        // Debut output format. In case you need it.
        // LLModel.OUTPUT_DEBUG=true;

        return new LLModelService(getLLModel());

    }

    @Bean
    LLModel getLLModel(){
        Path modelPath = Path.of(yamlConfig.getModelBasePath() + yamlConfig.getModelFilename());
        return new LLModel(modelPath);
    }
}
