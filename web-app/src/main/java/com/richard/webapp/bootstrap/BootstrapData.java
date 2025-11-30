package com.richard.webapp.bootstrap;

import com.richard.webapp.domain.Author;
import com.richard.webapp.domain.Book;
import com.richard.webapp.repositpry.AuthorRepository;
import com.richard.webapp.repositpry.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private  final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ericBook = new Book();
        ericBook.setTitle("Domain Driven Design");
        ericBook.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book ericBookSaved = bookRepository.save(ericBook);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book rodBook = new Book();
        rodBook.setTitle("J2EE Development without EJB");
        rodBook.setIsbn("654321");

        Author rodSaved = authorRepository.save(rod);
        Book rodBookSaved = bookRepository.save(rodBook);

        ericSaved.getBooks().add(ericBookSaved);
        rodSaved.getBooks().add(rodBookSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
    }
}
