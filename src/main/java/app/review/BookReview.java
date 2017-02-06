package app.review;

import org.springframework.data.annotation.Id;

public class BookReview {

    @Id
    private String id;

    private String title;

    private String description;

    private int rating;

    public BookReview() {}

    public BookReview(String title, String description, int rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}