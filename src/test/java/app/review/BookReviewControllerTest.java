package app.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookReviewControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    BookReviewController controller;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeClass
    public void globalPreparation() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @AfterMethod
    public void preparation() throws Exception {
        controller.repository.deleteAll();
    }

    @Test
    public void getReviewTest() throws Exception {
        mockMvc.perform(get("/reviews").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addReviewTest() throws Exception {
        BookReview bookReview = new BookReview("testTitle", "testDescription", 30);

        mockMvc.perform(post("/reviews").contentType(MediaType.APPLICATION_JSON).content(JsonConverter.convertObjectToJsonBytes(bookReview)))
                .andExpect(status().isOk());

        assertEquals(controller.repository.findAll().size(), 1);
    }

    @Test
    public void updateReviewTest() throws Exception {
        BookReview bookReview = new BookReview("testTitle", "testDescription", 30);
        controller.repository.save(bookReview);

        bookReview.setDescription("changedDescription");
        mockMvc.perform(put("/reviews").contentType(MediaType.APPLICATION_JSON).content(JsonConverter.convertObjectToJsonBytes(bookReview)))
                .andExpect(status().isOk());

        BookReview retrievedBook = controller.repository.findByTitle(bookReview.getTitle());
        assertEquals(retrievedBook.getDescription(), "changedDescription");
    }

    @Test
    public void deleteReviewTest() throws Exception {
        BookReview bookReview = new BookReview("testTitle", "testDescription", 30);
        controller.repository.save(bookReview);

        bookReview.setDescription("changedDescription");
        mockMvc.perform(delete("/reviews/testTitle").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        BookReview retrievedBook = controller.repository.findByTitle(bookReview.getTitle());
        assertNull(retrievedBook);
    }

}