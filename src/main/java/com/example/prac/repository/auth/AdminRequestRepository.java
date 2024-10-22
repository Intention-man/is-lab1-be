package com.example.prac.repository.auth;

import com.example.prac.model.authEntity.AdminRequest;
import com.example.prac.model.authEntity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRequestRepository extends CrudRepository<AdminRequest, Long>,
        PagingAndSortingRepository<AdminRequest, Long> {
    Optional<AdminRequest> findByRequester(User requester);
}

