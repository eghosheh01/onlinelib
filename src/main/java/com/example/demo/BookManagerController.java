package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookManagerController {
    @Autowired
    public BookManagerController(BookService bookService) {
        this.bookService = bookService;
    }

    private final BookService bookService;

    @GetMapping("load")
    public void loadData() {
        Book book2 = new Book("Test2 isbn", "Test2 name", "Test2 author name", "Test2 description");
        bookService.createBook(book2);
        Book book3 = new Book("Test3 isbn", "Test3 name", "Test3 author name", "Test3 description");
        bookService.createBook(book3);
        Book book4 = new Book("Test4 isbn", "Test4 name", "Test4 author name", "Test4 description");
        bookService.createBook(book4);
        Book book5 = new Book("Test5 isbn", "Test5 name", "Test5 author name", "Test5 description");
        bookService.createBook(book5);


    }

    @GetMapping("list")
    public List<Book> listBooks() {
        List<Book> booksInDb = bookService.findAllBooks();
        return booksInDb;
    }

    @GetMapping("search")
    public List<Book> searchBook(@Param("keyword") String keyword) {
        List<Book> foundBooks = bookService.searchBooks(keyword);
        return foundBooks;
    }

    @RequestMapping("/book/{id}")
    public Book findBookById(@PathVariable("id") Long id) {
        final Book book = bookService.findBookById(id);
        return book;
    }

    @RequestMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "deleted";
    }
// This is a test

}
