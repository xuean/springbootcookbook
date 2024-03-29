package com.example.bookpub;

import com.example.bookpub.model.Author;
import com.example.bookpub.model.Book;
import com.example.bookpub.model.Publisher;
import com.example.bookpub.repository.AuthorRepository;
import com.example.bookpub.repository.BookRepository;
import com.example.bookpub.repository.PublisherRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;

public class StartupRunner implements CommandLineRunner {
  protected final Log logger = LogFactory.getLog(getClass());
  @Autowired
  private DataSource ds;
  @Autowired private BookRepository bookRepository;
  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private PublisherRepository publisherRepository;

  @Override
  public void run(String... args) throws Exception {
    logger.info("Hello");
    logger.info("DataSource: " + ds.toString());
    logger.info("Number of books: " +  bookRepository.count());
   /* Author author = new Author("Alex", "Antonov");
    author = authorRepository.save(author);
    Publisher publisher = new Publisher("Packt");
    publisher = publisherRepository.save(publisher);
    Book book = new Book("978-1-78528-415-1", "Spring Boot Recipes", author, publisher);
    bookRepository.save(book);*/
  }
  @Scheduled(initialDelay = 1000, fixedRate = 10000)
  public void run() throws Exception {
    logger.info("Number of books: no-arg" +  bookRepository.count());
  }
}
