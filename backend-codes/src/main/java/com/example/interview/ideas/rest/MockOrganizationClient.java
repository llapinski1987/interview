package com.example.interview.ideas.rest;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * Just some mock organization client.
 */
@Service
public class MockOrganizationClient implements OrganizationClient {
    @Override
    public Map<String, Integer> getNumberOfCodesToGenerate() {
        return Collections.singletonMap(UUID.randomUUID().toString(), 100);
    }
}
