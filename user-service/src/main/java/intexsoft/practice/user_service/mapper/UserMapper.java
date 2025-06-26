package intexsoft.practice.user_service.mapper;

import intexsoft.practice.user_service.dto.request.RegisterUserRequest;
import intexsoft.practice.user_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterUserRequest registerUserRequest);
}
