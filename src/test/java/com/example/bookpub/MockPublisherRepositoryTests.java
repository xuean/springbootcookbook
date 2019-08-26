package com.example.bookpub;

import com.example.bookpub.repository.PublisherRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.reset;

@RunWith(SpringRunner.class)
// WebEnvironment.NONE. This is to inform Spring Boot that we don't want a full application web server to be initialized for this test,
// since we will only be interacting with the repository object, without making calls to controllers or using any part of the WebMvc stack.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//Creating tests using mock objects
public class MockPublisherRepositoryTests {
  //The @MockBean annotation instructs Spring that this dependency is not a real instance,
  // but a mock object currently backed by the Mockito framework.
  @MockBean
  private PublisherRepository repository;

  @Before
  public void setupPublisherRepositoryMock() {
    given(repository.count()).willReturn(5L);
  }

  @Test
  public void publishersExist() {
    assertThat(repository.count()).isEqualTo(5L);
  }

  @After
  public void resetPublisherRepositoryMock() {
    // in ideal situation, there is no need to reset every time.
    reset(repository);
  }
}
