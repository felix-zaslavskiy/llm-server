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

    @Operation(summary = "Generate text based on a prompt. Prompt is directly sent to LLM to generate followup characters")
    @ApiResponse(responseCode = "200", description = "The completed text",
            content = { @Content(mediaType = "text/plain") })
    @PostMapping(value = "/generate-text", consumes = "text/plain")
    public String generateText(@RequestBody String prompt,
                               @Parameter(name = "maxTokens", description = "What is the maximum number of tokens to produce in the output.")
                               @RequestParam(required = false) Integer maxTokens
    ) {

        LLModelService.GenerationOptions options = new LLModelService.GenerationOptions();
        options.maxTokens = maxTokens;
        return llModelService.generateText(prompt, false, options);
    }

    @Operation(summary = "Generate text but wrap the prompt in a simple instruction following template.")
    @ApiResponse(responseCode = "200", description = "The completed text",
            content = { @Content(mediaType = "text/plain") })
    @PostMapping(value = "/generate-text-using-instruction-following-template", consumes = "text/plain")
    public String generateText2(@RequestBody String prompt,
                                       @Parameter(name = "maxTokens", description = "What is the maximum number of tokens to produce in the output.")
                                       @RequestParam(required = false) Integer maxTokens
    ) {
        LLModelService.GenerationOptions options = new LLModelService.GenerationOptions();
        options.maxTokens = maxTokens;
        return llModelService.generateText(prompt, true, options);
    }

    @PostMapping("/handle-chat")
    public String handleChat(@RequestBody String input) {
        return "Not supported";
    }
}