package com.example.interview.ideas.rest;

import java.util.Map;

public interface OrganizationClient {

    /**
     * Get number of codes to be generated per organization.
     *
     * @return Map of organization/num of codes to be generated
     */
    Map<String, Integer> getNumberOfCodesToGenerate();
}
