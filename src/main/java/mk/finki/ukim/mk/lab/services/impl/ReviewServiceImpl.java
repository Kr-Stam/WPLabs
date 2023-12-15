package mk.finki.ukim.mk.lab.services.impl;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.repository.jpa.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    void save(Review review){
        reviewRepository.save(review);
    }

    List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    Review findReviewById(long id){
        return reviewRepository.findById(id).orElse(null);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public List<Review> getReviewsByBook(Book book){
        return reviewRepository.findReviewsByBook_Id(book.getId());
    }
    public List<Review> getReviewsByBook(long id){
        return reviewRepository.findReviewsByBook_Id(id);
    }

    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }

    public Review findById(long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> reviewsFromTo(LocalDateTime from, LocalDateTime to){
        return reviewRepository.findAll().stream()
                .filter(review ->
                    review.getTimestamp().compareTo(from) > 0
                        && review.getTimestamp().compareTo(to) < 0
                ).collect(Collectors.toList());
    }
}
