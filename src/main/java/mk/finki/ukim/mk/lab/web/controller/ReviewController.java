package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.services.impl.ReviewServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewController {

    private ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("reviews/{id}/delete")
    String deleteReview(@PathVariable long id){
        Review review = reviewService.findById(id);
        reviewService.deleteReview(id);

        return "redirect:/books/" + review.getBook().getId();
    }
}
