package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    Optional<BookMark> findById(Long id);
}
