package com.example.interview.ideas.services;

import com.example.interview.openapi.api.CodesApiDelegate;
import com.example.interview.openapi.model.Code;
import com.example.interview.openapi.model.GetInactiveCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Custom implementation of the Code API delegate.
 */
@Service
@RequiredArgsConstructor
public class CodeApiDelegateImpl implements CodesApiDelegate {
    private final CodeServiceInterface codeService;

    @Override
    public ResponseEntity<List<Code>> getCodes(GetInactiveCodeRequest getInactiveCodeRequest) {
        List<Code> inactiveCodes = codeService.getCodes(getInactiveCodeRequest.getOrganisationId(),
                getInactiveCodeRequest.getRequestedCode());
        return new ResponseEntity<>(inactiveCodes, HttpStatus.OK);
    }
}
