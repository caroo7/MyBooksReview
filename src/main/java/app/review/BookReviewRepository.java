package app.review;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookReviewRepository extends MongoRepository<BookReview, String> {

    BookReview findByTitle(String title);

}