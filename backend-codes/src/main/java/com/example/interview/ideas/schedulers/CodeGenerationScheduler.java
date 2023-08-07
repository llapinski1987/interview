package com.example.interview.ideas.schedulers;

import com.example.interview.openapi.model.CodeStatusEnum;

public interface CodeGenerationScheduler {

    void generate();

    CodeStatusEnum getType();
}
