package com.example.lab4;

import com.example.lab4.models.Author;
import com.example.lab4.models.Book;
import com.example.lab4.models.Genre;
import com.example.lab4.repo.AuthorRepository;
import com.example.lab4.repo.BookRepository;
import com.example.lab4.repo.GenreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class Lab4ApplicationTests {

	@Container
	public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

	@DynamicPropertySource
	static void dynamicProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysql::getJdbcUrl);
		registry.add("spring.datasource.username", mysql::getUsername);
		registry.add("spring.datasource.password", mysql::getPassword);
//		registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
	}

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private GenreRepository genreRepository;

	@AfterEach
	void cleanUpDatabase() {
		System.out.println("Cleaning up database...");
		bookRepository.deleteAll();
		System.out.println("Books deleted.");
		genreRepository.deleteAll();
		System.out.println("Genres deleted.");
		authorRepository.deleteAll();
		System.out.println("Authors deleted.");
	}

	@Test
	void testAddAuthor() {
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Doe");

		System.out.println("Saving Author: " + author);
		authorRepository.save(author);

		Optional<Author> retrievedAuthor = authorRepository.findById(author.getId());
		System.out.println("Retrieved Author: " + retrievedAuthor);

		assertThat(retrievedAuthor).isPresent();
		assertThat(retrievedAuthor.get().getFirstName()).isEqualTo("John");
		assertThat(retrievedAuthor.get().getLastName()).isEqualTo("Doe");
	}

	@Test
	void testFindAllAuthors() {
		Author author1 = new Author();
		author1.setFirstName("Alice");
		author1.setLastName("Smith");

		Author author2 = new Author();
		author2.setFirstName("Bob");
		author2.setLastName("Brown");

		System.out.println("Saving Authors: " + author1 + ", " + author2);
		authorRepository.save(author1);
		authorRepository.save(author2);

		Iterable<Author> authors = authorRepository.findAll();
		System.out.println("All Authors: " + authors);

		assertThat(authors).hasSize(2);
	}

	@Test
	void testAddGenre() {
		Genre genre = new Genre();
		genre.setName("Science Fiction");

		System.out.println("Saving Genre: " + genre);
		genreRepository.save(genre);

		Optional<Genre> retrievedGenre = genreRepository.findById(genre.getId());
		System.out.println("Retrieved Genre: " + retrievedGenre);

		assertThat(retrievedGenre).isPresent();
		assertThat(retrievedGenre.get().getName()).isEqualTo("Science Fiction");
	}

	@Test
	void testFindAllGenres() {
		Genre genre1 = new Genre();
		genre1.setName("Horror");

		Genre genre2 = new Genre();
		genre2.setName("Fantasy");

		System.out.println("Saving Genres: " + genre1 + ", " + genre2);
		genreRepository.save(genre1);
		genreRepository.save(genre2);

		Iterable<Genre> genres = genreRepository.findAll();
		System.out.println("All Genres: " + genres);

		assertThat(genres).hasSize(2);
	}

	@Test
	void testAddBook() {
		Author author = new Author();
		author.setFirstName("Jane");
		author.setLastName("Doe");
		authorRepository.save(author);

		Genre genre = new Genre();
		genre.setName("Fiction");
		genreRepository.save(genre);

		Book book = new Book();
		book.setTitle("Test Book");
		book.setIsbn("123-456-789");
		book.setYearOfPub("2024");
		book.setAuthor(author);
		book.setGenre(genre);

		System.out.println("Saving Book: " + book);
		bookRepository.save(book);

		Optional<Book> retrievedBook = bookRepository.findById(book.getId());
		System.out.println("Retrieved Book: " + retrievedBook);

		assertThat(retrievedBook).isPresent();
		assertThat(retrievedBook.get().getTitle()).isEqualTo("Test Book");
		assertThat(retrievedBook.get().getAuthor().getFirstName()).isEqualTo("Jane");
		assertThat(retrievedBook.get().getGenre().getName()).isEqualTo("Fiction");
	}

	@Test
	void testFindAllBooks() {
		// Arrange
		Author author = new Author();
		author.setFirstName("Mark");
		author.setLastName("Twain");
		authorRepository.save(author);

		Genre genre = new Genre();
		genre.setName("Adventure");
		genreRepository.save(genre);

		Book book1 = new Book();
		book1.setTitle("Book 1");
		book1.setIsbn("111-111-111");
		book1.setYearOfPub("2020");
		book1.setAuthor(author);
		book1.setGenre(genre);

		Book book2 = new Book();
		book2.setTitle("Book 2");
		book2.setIsbn("222-222-222");
		book2.setYearOfPub("2021");
		book2.setAuthor(author);
		book2.setGenre(genre);

		System.out.println("Saving Books: " + book1 + ", " + book2);
		bookRepository.save(book1);
		bookRepository.save(book2);

		Iterable<Book> books = bookRepository.findAll();
		System.out.println("All Books: " + books);

		assertThat(books).hasSize(2);
	}
}
