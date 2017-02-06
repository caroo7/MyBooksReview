package app.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/reviews")
@Transactional
public class BookReviewController {

    @Autowired
    BookReviewRepository repository;

    @GetMapping()
    public List<BookReview> getBookReviews() {
        return repository.findAll();
    }

    @PostMapping()
    public String addReview(@RequestBody BookReview bookReview) {
        BookReview retrievedBook = repository.save(bookReview);
        if (retrievedBook == null) {
            return "Cannot save passed review";
        }
        return "Book with title: " + retrievedBook.getTitle() + " was successfully saved";
    }

    @PutMapping
    public String updateReview(@RequestBody BookReview bookReview) {
        BookReview retrievedBook = repository.findByTitle(bookReview.getTitle());
        if(retrievedBook == null) {
            return "Cannot update review";
        }
        retrievedBook.setDescription(bookReview.getDescription());
        retrievedBook.setRating(bookReview.getRating());
        repository.save(retrievedBook);
        return "Book with title: " + retrievedBook.getTitle() + " was updated";
    }

    /*@DeleteMapping("/{title}")
    public String deleteReview(@PathVariable("title") String title) {
        BookReview reviewToDelete = repository.findByTitle(title);
        if (reviewToDelete == null) {
            return "Cannot delete passed review";
        }
        repository.delete(reviewToDelete);
        return "Book with title: " + reviewToDelete.getTitle() + " was successfully deleted";
    }*/

}