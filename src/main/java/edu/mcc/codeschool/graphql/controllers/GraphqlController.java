package edu.mcc.codeschool.graphql.controllers;

import edu.mcc.codeschool.graphql.models.Author;
import edu.mcc.codeschool.graphql.models.Book;
import edu.mcc.codeschool.graphql.models.Genre;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class GraphqlController {
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    @QueryMapping
    public List<Author> getAuthors() {
        return authors;
    }

    @QueryMapping
    public List<Book> getBooks() {
        return books;
    }

    @QueryMapping
    public Author getAuthorById(@Argument UUID authorId) {
        return authors.stream().filter(author -> author.getId().equals(authorId)).findFirst().orElse(null);
    }

    @MutationMapping
    public void deleteAuthorById(@Argument UUID authorId) {
        authors = authors.stream().filter(author -> !author.getId().equals(authorId)).toList();
    }

    @MutationMapping
    public Author createAuthor(@Argument String firstName, @Argument String lastName, @Argument Integer numOfBooksPublished) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setNumOfBooksPublished(numOfBooksPublished);
        author.setId(UUID.randomUUID());

        authors.add(author);
        return author;
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument UUID authorId, @Argument Integer releaseYear, @Argument Integer pages, @Argument String publisher, @Argument Boolean isHardCover, @Argument Genre genre){
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle(title);
        book.setAuthorId(authorId);
        book.setReleaseYear(releaseYear);
        book.setPages(pages);
        book.setPublisher(publisher);
        book.setHardCover(isHardCover);
        book.setGenre(genre);

        books.add(book);
        return book;
    }

    @SchemaMapping(typeName = "Author", field = "books")
    public List<Book> getAuthorsBooks(Author author) {
        return books.stream().filter(book -> book.getAuthorId().toString().equals(author.getId().toString())).toList();
    }

    @SchemaMapping(typeName="Book", field="author")
    private Author getBooksAuthor(Book book) {
        return authors.stream().filter(a -> a.getId().equals(book.getAuthorId())).findFirst().orElse(null);
    }

}
