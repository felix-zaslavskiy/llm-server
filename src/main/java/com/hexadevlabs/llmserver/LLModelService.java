package com.hexadevlabs.llmserver;

import com.hexadevlabs.gpt4all.LLModel;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LLModelService {

    // Inject LLModel instance (assuming it's a bean or creates one)
    private final LLModel llmodel;

    public LLModelService(LLModel llmodel) {
        this.llmodel = llmodel;
    }

    public String generateText(String prompt) {

        String fullPrompt = "### Human:\n" + prompt + "\n### Assistant:";
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
