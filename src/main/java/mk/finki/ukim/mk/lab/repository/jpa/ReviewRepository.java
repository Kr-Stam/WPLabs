package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
//    List<Review> getAll();
    Review getReviewById(long id);
    void deleteById(long id);

    List<Review> findReviewsByBook_Id(long id);

}
