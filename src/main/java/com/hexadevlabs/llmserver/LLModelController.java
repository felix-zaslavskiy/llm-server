package com.hexadevlabs.llmserver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LLModelController {

    private final LLModelService llModelService;

    public LLModelController(LLModelService llModelService) {
        this.llModelService = llModelService;
    }

    @Operation(summary = "Generate text based on a prompt.")
    @ApiResponse(responseCode = "200", description = "The completed text",
            content = { @Content(mediaType = "text/plain") })
    @PostMapping(value = "/generate-text", consumes = "text/plain")
    public String generateText(@RequestBody String prompt,

           @Parameter(name = "useInstructionTemplate", description = "Generate text but wrap the prompt in a simple instruction following template.")
           @RequestParam(required = false, defaultValue = "true") Boolean useInstructionTemplate,

           @Parameter(name = "maxTokens", description = "What is the maximum number of tokens to produce in the output.")
           @RequestParam(required = false) Integer maxTokens,

           @Parameter(name = "temperature", description = "Value from 0 to 1 that controls haw random the generations of LLM will be.  High temperature closer to 1 gives more creative outputs while low temperature closer to 0 produce more precise outputs.")
           @RequestParam(required = false) Float temperature
    ) {

        // Validate the format of temperature
        if(temperature!=null){
            if(temperature <0.0f || temperature > 1.0f){
                throw new IllegalArgumentException("Temperature must be 0.0 to 1.0");
            }
        }
        LLModelService.GenerationOptions options = new LLModelService.GenerationOptions();
        options.maxTokens = maxTokens;
        options.useInstructionTemplate = useInstructionTemplate;
        return llModelService.generateText(prompt, options);
    }

    @PostMapping("/handle-chat")
    public String handleChat(@RequestBody String input) {
        return "Not supported";
    }
}