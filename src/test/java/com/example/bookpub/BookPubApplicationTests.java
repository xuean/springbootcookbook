package com.example.bookpub;

import com.example.bookpub.model.Book;
import com.example.bookpub.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@Sql(scripts = "classpath:/test-data.sql")
public class BookPubApplicationTests {
  @Autowired
  private WebApplicationContext context;

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private BookRepository repository;

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

  @Autowired
  private DataSource ds;

  private static boolean loadDataFixtures = true;

  @Before
	public void setupMockMvc() {
   // mockMvc = webAppContextSetup(context).build();
	}
 /* @Before
  public void loadDataFixtures() {
    if (loadDataFixtures) {
      ResourceDatabasePopulator populator =
          new ResourceDatabasePopulator(context.getResource("classpath:/test-data.sql"));
      DatabasePopulatorUtils.execute(populator, ds);
      loadDataFixtures = false;
    }
  }*/

  @Test
	public void contextLoads() {
		assertEquals(3, repository.count());
	}
	@Test
	public void webappBookIsbnApi() {
		Book book =	restTemplate.getForObject("http://localhost:" +	 port + "/books/978-1-78528-415-1", Book.class);
		assertNotNull(book);
		assertEquals("Packt", book.getPublisher().getName());
	}

	@Test
	public void webappPublisherApi() throws Exception {
		mockMvc.perform(get("/publishers/1"))
				.andExpect(status().isOk()).andExpect(content()
				.contentType(MediaType.parseMediaType("application/hal+json;charset=UTF-8")))
				.andExpect(content().string(containsString("Packt")))
				.andExpect(jsonPath("$.name").value("Packt"));
	}


}
