package com.example.interview.ideas.repos;

import com.example.interview.openapi.model.Code;
import com.example.interview.openapi.model.CodeStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends MongoRepository<Code, String> {

    @Query("{organisationId: ?0, codeStatus :?1}")
    Page<Code> findCodesByStatusAndLimit(String orgId, CodeStatusEnum codeStatus, Pageable pageable);

    int countAllByCodeStatusAndOrganisationId(CodeStatusEnum codeStatus, String organisationId);

}
