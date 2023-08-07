package com.example.interview.ideas.services;

import com.example.interview.openapi.model.Code;
import com.example.interview.openapi.model.CodeStatusEnum;

import java.util.List;

/**
 * Interface for Idea service.
 */
public interface CodeServiceInterface {

    List<Code> getCodes(String organisationId, int numOfCodes);

    void generateCodes(int maxNumberOfCodes, String organisationId, CodeStatusEnum codeStatusEnum);
}
