package com.hexadevlabs.llmserver;

import com.hexadevlabs.gpt4all.LLModel;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;


@Service
public class LLModelService {

    public static class GenerationOptions {
        // NULL means use default.
        Integer maxTokens;
    }

    // Inject LLModel instance (assuming it's a bean or creates one)
    private final LLModel llmodel;

    public LLModelService(LLModel llmodel) {
        this.llmodel = llmodel;
    }

    public String generateText(String prompt, boolean justPrompt, GenerationOptions generationOptions) {

        String fullPrompt;
        if(!justPrompt){
            fullPrompt="### Human:\n" + prompt + "\n### Assistant:";
        } else {
            fullPrompt = prompt;
        }

        LLModel.GenerationConfig config =
                LLModel.config()
                        .withNPredict(4096)
                        .withRepeatLastN(64)
                        .build();

        return llmodel.generate(fullPrompt, config, false);

    }

    @PreDestroy
    private void  destroy() throws Exception {
        llmodel.close();
    }

}
