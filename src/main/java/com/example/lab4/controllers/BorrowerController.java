package com.example.lab4.controllers;

import com.example.lab4.models.Borrower;
import com.example.lab4.repo.BookRepository;
import com.example.lab4.repo.BorrowerRepository;
import com.example.lab4.repo.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BorrowerController {
    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;

    @GetMapping("/borrowers")
    public String borrowerMain(Model model) {
        Iterable<Borrower> borrowers = borrowerRepository.findAll();
        model.addAttribute("borrowers", borrowers);
        return "borrowers-main";
    }

    @GetMapping("/borrowers/add")
    public String addBorrowerForm(Model model) {
        model.addAttribute("borrower", new Borrower());
        model.addAttribute("books", bookRepository.findAll());
        return "borrower-add";
    }

    @PostMapping("/borrowers/add")
    public String addBorrowerSubmit(@ModelAttribute Borrower borrower) {
        borrowerRepository.save(borrower);
        return "redirect:/borrowers";
    }

}
