package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.library.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
}
