package com.example.books.service;

import com.example.books.model.dto.AuthorDTO;
import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<BookDTO> getBookById(Long bookId) {
        return this.bookRepository.findById(bookId)
                .map(this::map);
    }

    private BookDTO map(BookEntity bookEntity) {
        return new BookDTO()
                .setId(bookEntity.getId())
                .setTitle(bookEntity.getTitle())
                .setIsbn(bookEntity.getIsbn())
                .setAuthor(new AuthorDTO().setName(bookEntity.getAuthor().getName()));
    }
}
