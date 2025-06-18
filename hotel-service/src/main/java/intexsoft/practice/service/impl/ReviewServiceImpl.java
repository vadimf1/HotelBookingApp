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
import intexsoft.practice.util.exceptionCode.ClientExceptionCode;
import intexsoft.practice.util.exceptionCode.HotelExceptionCode;
import intexsoft.practice.util.exceptionCode.ReviewExceptionCode;
import intexsoft.practice.util.exceptionCode.RoomExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public void addReview(AddReviewDto addReviewDto) {
        Review review = reviewMapper.toEntity(addReviewDto);
        review.setHotel(
                hotelRepository.findById(addReviewDto.getHotelId())
                        .orElseThrow(() -> new ServiceException(HotelExceptionCode.HOTEL_NOT_FOUNT_BY_ID.getMessage() + addReviewDto.getHotelId()))

        );
        review.setRoom(
                roomRepository.findById(addReviewDto.getRoomId())
                        .orElseThrow(() -> new ServiceException(RoomExceptionCode.ROOM_NOT_FOUNT_BY_ID.getMessage() + addReviewDto.getRoomId()))

        );
        review.setClient(
                clientRepository.findById(addReviewDto.getClientId())
                        .orElseThrow(() -> new ServiceException(ClientExceptionCode.CLIENT_NOT_FOUNT_BY_ID.getMessage() + addReviewDto.getClientId()))
        );
        reviewRepository.save(review);
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
                .orElseThrow(() -> new ServiceException(ReviewExceptionCode.REVIEW_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public void updateReview(UUID id, UpdateReviewDto updateReviewDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ReviewExceptionCode.REVIEW_NOT_FOUNT_BY_ID.getMessage() + id));
        if (updateReviewDto.getHotelId() != null) {
            review.setHotel(
                    hotelRepository.findById(updateReviewDto.getHotelId())
                            .orElseThrow(() -> new ServiceException(HotelExceptionCode.HOTEL_NOT_FOUNT_BY_ID.getMessage() + updateReviewDto.getHotelId()))

            );
        }
        if (updateReviewDto.getRoomId() != null) {
            review.setRoom(
                    roomRepository.findById(updateReviewDto.getRoomId())
                            .orElseThrow(() -> new ServiceException(RoomExceptionCode.ROOM_NOT_FOUNT_BY_ID.getMessage() + updateReviewDto.getRoomId()))

            );
        }
        if (updateReviewDto.getClientId() != null) {
            review.setClient(
                    clientRepository.findById(updateReviewDto.getClientId())
                            .orElseThrow(() -> new ServiceException(ClientExceptionCode.CLIENT_NOT_FOUNT_BY_ID.getMessage() + updateReviewDto.getClientId()))
            );
        }
        reviewMapper.updateEntityFromDto(updateReviewDto, review);
        reviewRepository.save(review);
    }

    public void deleteReviewById(UUID id) {
        reviewRepository.deleteById(id);
    }
}
