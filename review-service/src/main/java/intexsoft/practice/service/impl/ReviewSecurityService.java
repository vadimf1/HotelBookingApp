package intexsoft.practice.service.impl;

import intexsoft.practice.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewSecurityService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public boolean checkOwnership(UUID reviewId) {
        String currentUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return reviewRepository.findById(reviewId)
                .map(review -> review.getUser().getId().toString().equals(currentUserId))
                .orElse(false);
    }
}

