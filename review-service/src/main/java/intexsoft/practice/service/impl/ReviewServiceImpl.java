package intexsoft.practice.service.impl;

import intexsoft.practice.dto.AddReviewDto;
import intexsoft.practice.dto.ResponseReviewDto;
import intexsoft.practice.dto.UpdateReviewDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.ReviewMapper;
import intexsoft.practice.model.Review;
import intexsoft.practice.repository.ClientRepository;
import intexsoft.practice.repository.HotelRepository;
import intexsoft.practice.repository.ReviewRepository;
import intexsoft.practice.repository.RoomRepository;
import intexsoft.practice.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ReviewMapper reviewMapper;
    private final ClientRepository clientRepository;

    public ResponseReviewDto addReview(AddReviewDto addReviewDto) {
        Review review = reviewMapper.toEntity(addReviewDto);
        review.setHotel(
                hotelRepository.findById(addReviewDto.getHotelId())
                        .orElseThrow(() -> new ServiceException("Hotel not found with ID: " + addReviewDto.getHotelId()))

        );
        review.setRoom(
                roomRepository.findById(addReviewDto.getRoomId())
                        .orElseThrow(() -> new ServiceException("Room not found with ID: " + addReviewDto.getRoomId()))

        );
        review.setUser(
                clientRepository.findById(addReviewDto.getUserId())
                        .orElseThrow(() -> new ServiceException("User not found with ID: " + addReviewDto.getUserId()))
        );
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    public List<ResponseReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    public ResponseReviewDto getReviewById(UUID id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ServiceException("Review not found with ID: " + id));
    }

    public ResponseReviewDto updateReview(UUID id, UpdateReviewDto updateReviewDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Review not found with ID: " + id));

        String currentUserId = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (!review.getUser().getId().toString().equals(currentUserId)) {
            throw new AccessDeniedException("Access denied: you can only manage your own reviews");
        }

        if (updateReviewDto.getHotelId() != null) {
            review.setHotel(
                    hotelRepository.findById(updateReviewDto.getHotelId())
                            .orElseThrow(() -> new ServiceException("Hotel not found with ID: " + updateReviewDto.getHotelId()))

            );
        }
        if (updateReviewDto.getRoomId() != null) {
            review.setRoom(
                    roomRepository.findById(updateReviewDto.getRoomId())
                            .orElseThrow(() -> new ServiceException("Room not found with ID: " + updateReviewDto.getRoomId()))

            );
        }
        if (updateReviewDto.getUserId() != null) {
            review.setUser(
                    clientRepository.findById(updateReviewDto.getUserId())
                            .orElseThrow(() -> new ServiceException("User not found with ID: " + updateReviewDto.getUserId()))
            );
        }
        reviewMapper.updateEntityFromDto(updateReviewDto, review);
        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.toDto(updatedReview);
    }

    public void deleteReviewById(UUID id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Review not found with ID: " + id));

        String currentUserId = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (!review.getUser().getId().toString().equals(currentUserId)) {
            throw new AccessDeniedException("Access denied: you can only manage your own reviews");
        }
        reviewRepository.deleteById(id);
    }
}
