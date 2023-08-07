package com.example.interview.ideas.services;

import com.example.interview.ideas.repos.CodeRepository;
import com.example.interview.openapi.model.Code;
import com.example.interview.openapi.model.CodeStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class CodeServiceTest {

    private final String ORGANISATION_ID = UUID.randomUUID().toString();

    @InjectMocks
    private CodeService unit;

    @Mock
    private CodeRepository codeRepositoryMock;

    @Captor
    private ArgumentCaptor<List<Code>> listOfCodesCaptor;

    @BeforeEach
    public void beforeMethod() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCodes_correctLimitAndStatusChange() {
        //given
        Code code1 = generateCode();
        Code code2 = generateCode();

        Page<Code> page = new PageImpl<>(List.of(code1, code2));

        when(codeRepositoryMock.findCodesByStatusAndLimit(eq(ORGANISATION_ID), eq(CodeStatusEnum.ACTIVE), any(PageRequest.class))).thenReturn(page);

        List<Code> result = unit.getCodes(ORGANISATION_ID, 2);

        //then
        assertNotNull(result);
        assertEquals(2, result.size());
        result.forEach(code -> {
            assertEquals(CodeStatusEnum.INACTIVE, code.getCodeStatus());
        });

        verify(codeRepositoryMock).saveAll(anyList());
    }

    @Test
    public void testGenerateCodes_correctCodesGeneration() {
        //given
        when(codeRepositoryMock.countAllByCodeStatusAndOrganisationId(CodeStatusEnum.ACTIVE, ORGANISATION_ID)).thenReturn(0);

        //when
        unit.generateCodes(2, ORGANISATION_ID, CodeStatusEnum.ACTIVE);

        //then
        verify(codeRepositoryMock).saveAll(listOfCodesCaptor.capture());

        List<Code> savedValues = listOfCodesCaptor.getValue();

        assertNotNull(savedValues);
        assertEquals(2, savedValues.size());

        for (Code code : savedValues) {
            assertNotNull(code.getId());
            assertNotNull(code.getCreated());
            assertEquals(ORGANISATION_ID, code.getOrganisationId());
            assertEquals(CodeStatusEnum.ACTIVE, code.getCodeStatus());
        }
    }

    @Test
    public void testGenerateCodes_maxLimitOfCodesAreAlreadyGenerated() {
        //given
        when(codeRepositoryMock.countAllByCodeStatusAndOrganisationId(CodeStatusEnum.ACTIVE, ORGANISATION_ID)).thenReturn(10);

        //when
        unit.generateCodes(2, ORGANISATION_ID, CodeStatusEnum.ACTIVE);

        //then
        verify(codeRepositoryMock).countAllByCodeStatusAndOrganisationId(CodeStatusEnum.ACTIVE, ORGANISATION_ID);
        verifyNoMoreInteractions(codeRepositoryMock);
    }

    private Code generateCode() {
        return Code.builder().id(UUID.randomUUID().toString()).organisationId(ORGANISATION_ID).codeStatus(CodeStatusEnum.ACTIVE).created(LocalDateTime.now().toString()).build();
    }
}