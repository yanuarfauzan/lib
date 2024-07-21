package com.domain.library.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.dto.responses.ResBorrowReturn;
import com.domain.library.dto.responses.ResBorrowReturnData;
import com.domain.library.entity.Books;
import com.domain.library.entity.Members;
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
    @Autowired
    private BookRepository bookRepository;

    public List<Members> getAllMember() {
        return memberRepository.findAll();
    }

    public ResBorrowReturnData<ResBorrowReturn> borrowBook(String memberId, String bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Buku dengan id " + bookId + " tidak ditemukan"));
        Members member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member dengan id " + memberId + " tidak ditemukan"));
        
        if (book.getStatus() == BookStatus.BORROWED && book.getStock() == 0) {
            return new ResBorrowReturnData<>("Gagal meminjam buku, buku sedang tidak tersedia", null);
        }
        if (member.getStatus() == MemberStatus.PENALIZED) {
            return new ResBorrowReturnData<>("Gagal meminjam buku, member sedang di pinalti", null);
        }

        Date today = new Date(System.currentTimeMillis());
        book.setMember(member);
        book.setStatus(BookStatus.BORROWED);
        book.setStock(book.getStock() - 1);
        book.setBorrowedDate(today);
        book.setDuDate(calculateDueDate(today, 7));
        bookRepository.save(book);
        ResBorrowReturn resBorrowReturn = new ResBorrowReturn();
        resBorrowReturn.setMemberId(memberId);
        resBorrowReturn.setBook(book);
        return new ResBorrowReturnData<>("Berhasil meminjam buku", resBorrowReturn);
    }

    private Date calculateDueDate(Date startDate, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        return new Date(calendar.getTimeInMillis());
    }

    public ResBorrowReturnData<ResBorrowReturn> returnBook(String memberId, String bookId) {
        Books book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("buku dengan id " + bookId + " tidak ditemukan"));
        Members member = memberRepository.findById(memberId).orElseThrow(() -> new ResourceNotFoundException("member dengan id " + memberId + " tidak ditemukan"));
        Members bookWithMember = book.getMember();
        if (book.getStatus() == BookStatus.AVAILABLE && member != bookWithMember) {
            return new ResBorrowReturnData<>("Gagal mengembalikan buku, member tidak meminjam buku ini", null);
        }
        Date today = new Date(System.currentTimeMillis());
        if (book.getDuDate().before(today)) {
            member.setStatus(MemberStatus.PENALIZED);
            member.setPenalizedDate(today);
            member.setPenaltyEndDate(calculateDueDate(today, 3));
            memberRepository.save(member);
        }
        book.setStatus(BookStatus.AVAILABLE);
        book.setBorrowedDate(null);
        book.setDuDate(null);
        book.setMember(null);
        bookRepository.save(book);
        ResBorrowReturn resBorrowReturn = new ResBorrowReturn();
        resBorrowReturn.setMemberId(memberId);
        resBorrowReturn.setBook(book);
        return new ResBorrowReturnData<>("Berhasil mengembalikan buku", resBorrowReturn);

    }


}
