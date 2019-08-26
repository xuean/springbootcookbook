package com.example.bookpub.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@AllArgsConstructor
//@Builder(toBuilder = true)
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@bookId")
public class Book {
  @Id
  //change @GeneratedValue to strategy = GenerationType.IDENTITY
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String isbn;
  private String title;
  private String description;

  @ManyToOne
  private Author author;
  @ManyToOne
  private Publisher publisher;

  @ManyToMany

  private List<Reviewer> reviewers;


  public Book(String isbn, String description, Author author, Publisher publisher) {
    this.isbn = isbn;
    this.description = description;
    this.author = author;
    this.publisher = publisher;
  }
}
