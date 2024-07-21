package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.library.entity.Members;

public interface MemberRepository extends JpaRepository<Members, String> {
    
}
