package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {
        var bookDto = this.bookService.getBookById(bookId);
        if (bookDto.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(bookDto.get());
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        var books = this.bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable("id") Long bookId) {
        this.bookService.deleteById(bookId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO newBook, UriComponentsBuilder uriComponentsBuilder) {
        var bookId = this.bookService.createBook(newBook);

        return ResponseEntity.created(uriComponentsBuilder.path("/api/books/{id}").build(bookId))
                .build();
    }
}
