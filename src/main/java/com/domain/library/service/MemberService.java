package com.domain.library.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.entity.Book;
import com.domain.library.entity.Member;
import com.domain.library.enums.BookStatus;
import com.domain.library.enums.MemberStatus;
import com.domain.library.exception.ResourceNotFoundException;
import com.domain.library.repository.BookRepository;
import com.domain.library.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    private BookRepository bookRepository;

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    public Object[] borrowBook(String memberId, String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Buku dengan id " + bookId + " tidak ditemukan"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member dengan id " + memberId + " tidak ditemukan"));
        Date today = new Date(System.currentTimeMillis());
        if (book.getStatus() == BookStatus.AVAILABLE && book.getStock() > 0) {
            if (member.getStatus() == MemberStatus.ACTIVE) {
                {
                    book.setMember(member);
                    book.setStatus(BookStatus.BORROWED);
                    book.setStock(book.getStock() - 1);
                    book.setBorrowedDate(today);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(today);
                    calendar.add(Calendar.DAY_OF_YEAR, 7);
                    Date dueDate = new Date(calendar.getTimeInMillis());
                    book.setDuDate(dueDate);
                    bookRepository.save(book);
                    
                    return new Object[] { memberId, book };
                }
            } else {
                throw new RuntimeException("Member dengan id " + memberId + " tidak aktif");
            }
        } else {
            throw new RuntimeException("Buku dengan id " + bookId + " tidak tersedia");
        }
    }
}
