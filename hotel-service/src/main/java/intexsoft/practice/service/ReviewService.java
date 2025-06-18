package intexsoft.practice.service;

import intexsoft.practice.dto.AddReviewDto;
import intexsoft.practice.dto.ResponseReviewDto;
import intexsoft.practice.dto.UpdateReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    void addReview(AddReviewDto addReviewDto);
    List<ResponseReviewDto> getAllReviews();
    ResponseReviewDto getReviewById(UUID id);
    void updateReview(UUID id, UpdateReviewDto updateReviewDto);
    void deleteReviewById(UUID id);
}
