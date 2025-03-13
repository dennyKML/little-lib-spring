package com.example.lab4.controllers;

import com.example.lab4.models.Borrower;
import com.example.lab4.models.Genre;
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
public class GenreController {
    private final GenreRepository genreRepository;

    @GetMapping("/genres")
    public String genreMain(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genres-main";
    }

    @GetMapping("/genres/add")
    public String addGenreForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre-add";
    }

    @PostMapping("/genres/add")
    public String addGenreSubmit(@ModelAttribute Genre genre) {
        genreRepository.save(genre);
        return "redirect:/genres";
    }
}
