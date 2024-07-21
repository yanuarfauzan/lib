package com.domain.library.entity;

import java.sql.Date;

import com.domain.library.enums.BookStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Books {
    @Id
    private String id;
    @Column(unique = true)
    private String code;
    private String title;
    private String author;
    private int stock;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private Date borrowedDate;
    private Date duDate;
    @JoinColumn(name = "member_id")
    @ManyToOne
    private Members member;
}
