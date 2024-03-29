package com.example.bookpub;

import com.example.bookpub.model.Author;
import com.example.bookpub.repository.AuthorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

//modulized test without mock whole container
@RunWith(SpringRunner.class)
//@DataJpaTest annotation doing the bulk of the declarations and workload to configure the test environment.
@DataJpaTest
public class JpaAuthorRepositoryTests {
  @Autowired
  private TestEntityManager mgr;

  @Autowired
  private AuthorRepository repository;

  @Test
  public void testAuthorEntityBinding() {
    Long id = mgr.persistAndGetId(createAuthor(),Long.class);
    Author author = repository.findById(id).get();
    assertThat(author.getFirstName()).isEqualTo("Mark");
    assertThat(author.getLastName()).isEqualTo("Twain");
  }

  private Author createAuthor() {
    return new Author("Mark", "Twain");
  }
}
