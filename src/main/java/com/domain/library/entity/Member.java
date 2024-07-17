package com.domain.library.entity;

import java.sql.Date;

import com.domain.library.enums.MemberStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    private String id;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private Date penalizedDate;
    private Date penaltyEndDate;
}
