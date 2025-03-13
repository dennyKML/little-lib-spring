package com.example.lab4.controllers;

import com.example.lab4.models.Author;
import com.example.lab4.models.Book;
import com.example.lab4.repo.AuthorRepository;
import com.example.lab4.repo.BookRepository;
import com.example.lab4.repo.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping("/books")
    public String bookMain(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books-main";
    }

    @GetMapping("/books/find")
    public String bookMainFindBySmth(Model model) {
        List<Book> books = bookRepository.findByYearOfPubAndGenreName("2020", "Психодел");
        model.addAttribute("books", books);
        return "books-main-find";
    }

    @GetMapping("/books/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "book-add";
    }

    @PostMapping("/books/add")
    public String addBookSubmit(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }
}
