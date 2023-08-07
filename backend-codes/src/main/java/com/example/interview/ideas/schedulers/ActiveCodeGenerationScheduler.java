package com.example.interview.ideas.schedulers;

import com.example.interview.ideas.rest.OrganizationClient;
import com.example.interview.ideas.services.CodeServiceInterface;
import com.example.interview.openapi.model.CodeStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

/**
 * Scheduler for generating ACTIVE codes.
 */
@Configuration
@RequiredArgsConstructor
public class ActiveCodeGenerationScheduler implements CodeGenerationScheduler {

    private final OrganizationClient organizationClient;
    private final CodeServiceInterface codeServiceInterface;

    /**
     * Scheduler running every 24 hours at midnight.
     */

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    @Override
    public void generate() {
        Map<String, Integer> maxNumberOfCodesPerOrganization = organizationClient.getNumberOfCodesToGenerate();

        for (String organisationId : maxNumberOfCodesPerOrganization.keySet()) {
            int numOfCodes = maxNumberOfCodesPerOrganization.get(organisationId);
            codeServiceInterface.generateCodes(numOfCodes, organisationId, getType());
        }
    }

    @Override
    public CodeStatusEnum getType() {
        return CodeStatusEnum.ACTIVE;
    }
}
