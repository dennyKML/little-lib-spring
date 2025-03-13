    package com.example.lab4.controllers;
    
    import com.example.lab4.models.Author;
    import com.example.lab4.repo.AuthorRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    
    @Controller
    @RequiredArgsConstructor
    public class AuthorController {
        private final AuthorRepository authorRepository;
    
        @GetMapping("/authors")
        public String authorsMain(Model model) {
            Iterable<Author> authors = authorRepository.findAll();
            model.addAttribute("authors", authors);
            return "authors-main";
        }
    
        @GetMapping("/authors/add")
        public String addAuthorForm(Model model) {
            model.addAttribute("author", new Author());
            return "author-add";
        }
    
        @PostMapping("/authors/add")
        public String addAuthorSubmit(@ModelAttribute Author author) {
            authorRepository.save(author);
            return "redirect:/authors";
        }
    }
