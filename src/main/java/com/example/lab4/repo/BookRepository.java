package com.example.lab4.repo;

import com.example.lab4.models.Book;
import com.example.lab4.models.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.genre g WHERE b.yearOfPub = :yearOfPub AND g.name = :genreName")
    List<Book> findByYearOfPubAndGenreName(@Param("yearOfPub") String yearOfPub, @Param("genreName") String genreName);
}