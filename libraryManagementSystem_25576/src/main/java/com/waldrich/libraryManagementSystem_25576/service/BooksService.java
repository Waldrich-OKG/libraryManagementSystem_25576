package com.waldrich.libraryManagementSystem_25576.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.waldrich.libraryManagementSystem_25576.dto.BookDTO;
import com.waldrich.libraryManagementSystem_25576.enums.BookCategory;
import com.waldrich.libraryManagementSystem_25576.model.Books;
import com.waldrich.libraryManagementSystem_25576.repository.BooksRepository;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public Books saveBook(Books book) {
        return booksRepository.save(book);
    }

    public Page<BookDTO> getAllBooks(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Books> bookPage = booksRepository.findAll(pageable);
        return bookPage.map(BookDTO::new);
    }

    public Page<BookDTO> searchBooks(String keyword, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Books> bookPage = booksRepository.searchBooks(keyword, pageable);
        return bookPage.map(BookDTO::new);
    }

    // public Page<BookDTO> searchBooks(String keyword, int page, int size, String sortBy, String direction) {
    //     Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    //     Pageable pageable = PageRequest.of(page, size, sort);

    //     Page<Books> bookPage = booksRepository
    //         .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrLanguageContainingIgnoreCaseOrYearOfPublicationContainingIgnoreCaseOrBookStatusContainingIgnoreCase(
    //             keyword, keyword, keyword, keyword, keyword, keyword, pageable
    //         );

    //     return bookPage.map(BookDTO::new);
    // }

    public Books getBookById(Long bookId) {
        return booksRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book with id " + bookId + " not found"));
    }

    public List<Books> getBooksByTitle(String title) {
        return booksRepository.findByTitleContaining(title);
    }

    public List<Books> getBooksByAuthor(String author) {
        return booksRepository.findByAuthorContaining(author);
    }

    public List<Books> getBooksByCategory(BookCategory category) {
        return booksRepository.findByCategory(category);
    }

    public Books updateBook(Long id, Books updatedBook) {
        Books existingBook = booksRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
        if(Objects.nonNull(updatedBook.getTitle()) && !updatedBook.getTitle().trim().isEmpty()) {
            existingBook.setTitle(updatedBook.getTitle());
        }
        if(Objects.nonNull(updatedBook.getAuthor()) && !updatedBook.getAuthor().trim().isEmpty()) {
            existingBook.setAuthor(updatedBook.getAuthor());
        }
        if(Objects.nonNull(updatedBook.getCategory())) {
            existingBook.setCategory(updatedBook.getCategory());
        }
        if(Objects.nonNull(updatedBook.getLanguage()) && !updatedBook.getLanguage().trim().isEmpty()) {
            existingBook.setLanguage(updatedBook.getLanguage());
        }
        if(Objects.nonNull(updatedBook.getYearOfPublication()) && !updatedBook.getYearOfPublication().trim().isEmpty()) {
            existingBook.setYearOfPublication(updatedBook.getYearOfPublication());
        }
        return booksRepository.save(existingBook);
        
    }

    public void deleteBook(Long id) {
        booksRepository.deleteById(id);
    }


    
}
