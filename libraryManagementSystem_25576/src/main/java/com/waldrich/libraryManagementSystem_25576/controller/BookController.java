package com.waldrich.libraryManagementSystem_25576.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waldrich.libraryManagementSystem_25576.dto.BookDTO;
import com.waldrich.libraryManagementSystem_25576.enums.BookCategory;
import com.waldrich.libraryManagementSystem_25576.model.Books;
import com.waldrich.libraryManagementSystem_25576.service.BooksService;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    
    @Autowired
    private BooksService booksService;

    @PostMapping(value = "/register", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> saveBook(@RequestBody Books book) {
        return ResponseEntity.ok(booksService.saveBook(book));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<BookDTO>> getAllBooks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "bookId") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(booksService.getAllBooks(page, size, sortBy, direction));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('LIBRARIAN','REGULAR')")
    public Page<BookDTO> searchBooks(
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "12") int size,
        @RequestParam(defaultValue = "title") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return booksService.searchBooks(keyword, page, size, sortBy, direction);
    }

    // @GetMapping("/search")
    // public Page<BookDTO> searchBooks(
    //     @RequestParam String keyword,
    //     @RequestParam(defaultValue = "0") int page,
    //     @RequestParam(defaultValue = "12") int size,
    //     @RequestParam(defaultValue = "title") String sortBy,
    //     @RequestParam(defaultValue = "asc") String direction
    // ) {
    //     return booksService.searchBooks(keyword, page, size, sortBy, direction);
    // }


    @GetMapping("/title")
    public ResponseEntity<List<Books>> getBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(booksService.getBooksByTitle(title));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Books>> getBooksByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(booksService.getBooksByAuthor(author));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Books>> getBooksByCategory(@RequestParam BookCategory category) {
        return ResponseEntity.ok(booksService.getBooksByCategory(category));
    }

    @GetMapping("/one/{bookId}")
    public ResponseEntity<Books> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(booksService.getBookById(bookId));
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping(value = "/update", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> updateBook(@RequestParam Long id, @RequestBody Books updatedBook) {
        return ResponseEntity.ok(booksService.updateBook(id, updatedBook));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        booksService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
