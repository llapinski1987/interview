package com.example.interview.ideas.services;

import com.example.interview.ideas.repos.CodeRepository;
import com.example.interview.openapi.model.Code;
import com.example.interview.openapi.model.CodeStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service for ideas main logic.
 */
@Service
@RequiredArgsConstructor
public class CodeService implements CodeServiceInterface {

    private final CodeRepository codeRepository;

    @Transactional
    @Override
    public List<Code> getCodes(String organisationId, int numOfCodes) {
        PageRequest pageRequest = PageRequest.of(0, numOfCodes, Sort.by("timestamp"));
        List<Code> codesByStatus = codeRepository.findCodesByStatusAndLimit(organisationId, CodeStatusEnum.ACTIVE, pageRequest).getContent();

        //Mark all of the returned codes as INACTIVE
        codesByStatus.forEach(code -> code.setCodeStatus(CodeStatusEnum.INACTIVE));
        codeRepository.saveAll(codesByStatus);

        return codesByStatus;
    }

    /**
     * Fill missing codes.
     *
     * @param maxNumberOfCodes max number of codes allowed
     * @param codeStatusEnum   generated code status
     */
    @Transactional
    @Override
    public void generateCodes(int maxNumberOfCodes, String organisationId, CodeStatusEnum codeStatusEnum) {
        generateMissingCodes(maxNumberOfCodes - codeRepository.countAllByCodeStatusAndOrganisationId(codeStatusEnum, organisationId),
                organisationId, codeStatusEnum);
    }

    private void generateMissingCodes(int numberOfCodes, String organisationId, CodeStatusEnum codeStatusEnum) {
        if (numberOfCodes > 0) {
            List<Code> codes = new ArrayList<>();
            String timestamp = LocalDateTime.now().toString();
            for (int i = 0; i < numberOfCodes; i++) {
                Code code = generateCode(organisationId, codeStatusEnum, timestamp);
                codes.add(code);
            }
            codeRepository.saveAll(codes);
        }
    }

    private Code generateCode(String organisationId, CodeStatusEnum codeStatusEnum, String timestamp) {
        return Code.builder()
                .organisationId(organisationId)
                .id(UUID.randomUUID().toString())
                .created(timestamp)
                .codeStatus(codeStatusEnum)
                .build();
    }
}
