package intexsoft.practice.mapper;

import intexsoft.practice.dto.AddReviewDto;
import intexsoft.practice.dto.ResponseReviewDto;
import intexsoft.practice.dto.UpdateReviewDto;
import intexsoft.practice.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ResponseReviewDto toDto(Review review);
    Review toEntity(AddReviewDto addReviewDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateReviewDto updateReviewDto, @MappingTarget Review review);
}
