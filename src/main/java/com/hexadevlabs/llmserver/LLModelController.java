package com.hexadevlabs.llmserver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LLModelController {

    private final LLModelService llModelService;

    public LLModelController(LLModelService llModelService) {
        this.llModelService = llModelService;
    }

    @PostMapping("/generate-text")
    public String generateText(@RequestBody String prompt) {
        return llModelService.generateText(prompt);
    }

    @PostMapping("/handle-chat")
    public String handleChat(@RequestBody String input) {
       //  return llModelService.handleChat(input);
        return "";
    }
}