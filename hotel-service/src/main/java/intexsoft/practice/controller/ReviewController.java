package intexsoft.practice.controller;

import intexsoft.practice.dto.AddReviewDto;
import intexsoft.practice.dto.ResponseReviewDto;
import intexsoft.practice.dto.UpdateReviewDto;
import intexsoft.practice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ResponseReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseReviewDto> getReviewById(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PostMapping
    public ResponseEntity<String> addReview(@Valid @RequestBody AddReviewDto addReviewDto) {
        reviewService.addReview(addReviewDto);
        return ResponseEntity.ok("Review added successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable UUID id, @Valid @RequestBody UpdateReviewDto updateReviewDto) {
        reviewService.updateReview(id, updateReviewDto);
        return ResponseEntity.ok("Review updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") UUID id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
