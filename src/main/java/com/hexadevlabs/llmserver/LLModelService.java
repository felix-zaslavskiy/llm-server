package com.hexadevlabs.llmserver;

import com.hexadevlabs.gpt4all.LLModel;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;


@Service
public class LLModelService {

    private boolean llmDebugMode;
    private String modelLoaded;

    public void setModelLoaded(String modelFilename) {
        modelLoaded = modelFilename;
    }

    public static class GenerationOptions {
        public Float temperature;
        public Boolean useInstructionTemplate;

        // NULL means use default.
        Integer maxTokens;
    }

    // Inject LLModel instance (assuming it's a bean or creates one)
    private final LLModel llmodel;

    public LLModelService(LLModel llmodel) {
        this.llmodel = llmodel;
    }

    public void setLlmDebugMode(boolean llmDebugMode){
        this.llmDebugMode = llmDebugMode;
    }

    public String generateText(String prompt, GenerationOptions generationOptions) {

        String fullPrompt;
        if(generationOptions.useInstructionTemplate){
            fullPrompt="### Human:\n" + prompt + "\n### Assistant:";
        } else {
            fullPrompt = prompt;
        }

        LLModel.GenerationConfig.Builder builder =
                LLModel.config()
                        .withNPredict(generationOptions.maxTokens!=null ? generationOptions.maxTokens : 200)
                        .withRepeatLastN(64);

        if(generationOptions.temperature != null){
            builder.withTemp(generationOptions.temperature);
        }

        LLModel.GenerationConfig config = builder.build();

        // Need better way than printing to standard output. Use logger instead.
        if(llmDebugMode) System.out.print(fullPrompt);

        String result =  llmodel.generate(fullPrompt, config, llmDebugMode);

        if(llmDebugMode) System.out.println();

        return result;

    }

    public String getModelInfo(){
        return "Current model loaded is: " + modelLoaded;
    }

    @PreDestroy
    private void  destroy() throws Exception {
        llmodel.close();
    }

}
